package com.intellij.lang.actionscript.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.WhitespacesBinders;
import com.intellij.lang.actionscript.ActionScriptElementTypes;
import com.intellij.lang.javascript.*;
import com.intellij.lang.javascript.parsing.FunctionParser;
import com.intellij.lang.javascript.parsing.StatementParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Konstantin.Ulitin
 */
public final class ActionScriptStatementParser extends StatementParser<ActionScriptParser> {
  ActionScriptStatementParser(ActionScriptParser parser) {
    super(parser);
  }

  @Override
  public void parseSourceElement() {
    final IElementType tokenType = builder.getTokenType();
    if (tokenType == JSTokenTypes.PACKAGE_KEYWORD) {
      parsePackage();
    }
    else if (tokenType == JSTokenTypes.AT) {
      builder.advanceLexer();
      parseAttributeBody();
    } else {
      super.parseSourceElement();
    }
  }

  private void parseAttributeBody() {
    final PsiBuilder.Marker attribute = builder.mark();
    if(!checkMatches(builder, JSTokenTypes.IDENTIFIER, "javascript.parser.message.expected.identifier")) {
      attribute.drop();
      return;
    }
    myJavaScriptParser.getFunctionParser().parseAttributeBody();
    attribute.done(JSStubElementTypes.ATTRIBUTE);
  }


  /** advances lexer */
  @Override
  protected boolean parseVarName(PsiBuilder.Marker var) {
    if (!isIdentifierToken(builder.getTokenType())) {
      builder.error(JavaScriptBundle.message("javascript.parser.message.expected.variable.name"));
      builder.advanceLexer();
      var.drop();
      return false;
    }

    myJavaScriptParser.getTypeParser().parseQualifiedTypeName();
    return true;
  }

  @Override
  protected void doParseStatement() {
    final IElementType firstToken = builder.getTokenType();
    if (firstToken == JSTokenTypes.DEFAULT_KEYWORD) {
      parseDefaultNsStatement();
      return;
    }

    if (firstToken == JSTokenTypes.IMPORT_KEYWORD) {
      parseImportStatement();
      return;
    }

    if (firstToken == JSTokenTypes.USE_KEYWORD) {
      parseUseNamespaceDirective();
      return;
    }

    if (firstToken == JSTokenTypes.GOTO_KEYWORD && isIdentifierToken(builder.lookAhead(1))) {
      final PsiBuilder.Marker statement = builder.mark();
      builder.advanceLexer();
      builder.advanceLexer();
      checkForSemicolon();
      statement.done(ActionScriptElementTypes.GOTO_STATEMENT);
      return;
    }

    if (firstToken == JSTokenTypes.INCLUDE_KEYWORD) {
      parseIncludeDirective();
      return;
    }

    if (firstToken == JSTokenTypes.NAMESPACE_KEYWORD) {
      PsiBuilder.Marker marker = startAttributeListOwner();
      if (parseNamespaceNoMarker(marker)) {
        return;
      }
    }

    super.doParseStatement();
  }

  void parseIncludeDirective() {
    LOG.assertTrue(builder.getTokenType() == JSTokenTypes.INCLUDE_KEYWORD);
    final PsiBuilder.Marker useNSStatement = builder.mark();
    builder.advanceLexer();
    checkMatches(builder, JSTokenTypes.STRING_LITERAL, "javascript.parser.message.expected.string.literal");
    checkForSemicolon();

    useNSStatement.done(ActionScriptElementTypes.INCLUDE_DIRECTIVE);
  }

  private boolean parseNamespaceNoMarker(final @NotNull PsiBuilder.Marker useNSStatement) {
    LOG.assertTrue(builder.getTokenType() == JSTokenTypes.NAMESPACE_KEYWORD);

    builder.advanceLexer();
    if (!JSKeywordSets.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
      useNSStatement.rollbackTo();
      return false;
    }

    myJavaScriptParser.getTypeParser().parseQualifiedTypeName();

    if (builder.getTokenType() == JSTokenTypes.EQ) {
      builder.advanceLexer();

      IElementType tokenType = builder.getTokenType();

      if (tokenType == JSTokenTypes.PUBLIC_KEYWORD) {
        builder.advanceLexer();
      } else if (tokenType == JSTokenTypes.STRING_LITERAL || tokenType == JSTokenTypes.IDENTIFIER) {
        myJavaScriptParser.getExpressionParser().parseExpression();
      } else {
        builder.error(JavaScriptBundle.message("javascript.parser.message.expected.string.literal"));
      }
    }
    checkForSemicolon();
    useNSStatement.done(ActionScriptElementTypes.NAMESPACE_DECLARATION);
    useNSStatement.setCustomEdgeTokenBinders(INCLUDE_DOC_COMMENT_AT_LEFT, WhitespacesBinders.DEFAULT_RIGHT_BINDER);
    return true;
  }

  private void parseDefaultNsStatement() {
    LOG.assertTrue(builder.getTokenType() == JSTokenTypes.DEFAULT_KEYWORD);
    final PsiBuilder.Marker statementMarker = builder.mark();
    final PsiBuilder.Marker marker = builder.mark();
    builder.advanceLexer();

    if (builder.getTokenType() == JSTokenTypes.IDENTIFIER &&
        "xml".equals(builder.getTokenText())
      ) {
      builder.advanceLexer();

      if (checkMatches(builder, JSTokenTypes.NAMESPACE_KEYWORD, "javascript.parser.message.expected.namespace")) {
        if (checkMatches(builder, JSTokenTypes.EQ, "javascript.parser.message.expected.equal")) {
          myJavaScriptParser.getExpressionParser().parseExpression();
        }
      }
    } else {
      builder.error(JavaScriptBundle.message("javascript.parser.message.expected.xml"));
    }
    marker.done(JSStubElementTypes.ASSIGNMENT_EXPRESSION);
    checkForSemicolon();
    statementMarker.done(JSElementTypes.EXPRESSION_STATEMENT);
  }

  @Override
  protected PsiBuilder.Marker startAttributeListOwner() {
    PsiBuilder.Marker marker = builder.mark();
    if (!isBlockBodyContext()) {
      PsiBuilder.Marker modifierListMarker = builder.mark();
      modifierListMarker.done(myJavaScriptParser.getFunctionParser().getAttributeListElementType());
    }
    return marker;
  }

  @Override
  public IElementType getVariableElementType() {
    return isBlockBodyContext() ? ActionScriptElementTypes.LOCAL_VARIABLE : ActionScriptElementTypes.ACTIONSCRIPT_VARIABLE;
  }

  public void parseUseNamespaceDirective() {
    final PsiBuilder.Marker useNSStatement = builder.mark();
    builder.advanceLexer();

    if (builder.getTokenType() != JSTokenTypes.NAMESPACE_KEYWORD) {
      builder.error(JavaScriptBundle.message("javascript.parser.message.expected.namespace"));
    } else {
      builder.advanceLexer();

      if (!myJavaScriptParser.getTypeParser().parseQualifiedTypeName()) {
        builder.error(JavaScriptBundle.message("javascript.parser.message.expected.typename.or.*"));
      }

      while(builder.getTokenType() == JSTokenTypes.COMMA) {
        builder.advanceLexer();
        if (!myJavaScriptParser.getTypeParser().parseQualifiedTypeName()) {
          builder.error(JavaScriptBundle.message("javascript.parser.message.expected.typename.or.*"));
          break;
        }
      }
    }
    checkForSemicolon();
    useNSStatement.done(ActionScriptElementTypes.USE_NAMESPACE_DIRECTIVE);
  }

  private void parsePackage() {
    final PsiBuilder.Marker _package = builder.mark();
    builder.advanceLexer();
    if (builder.getTokenType() == JSTokenTypes.IDENTIFIER) {
      myJavaScriptParser.getTypeParser().parseQualifiedTypeName();
    }

    if (builder.getTokenType() != JSTokenTypes.LBRACE) {
      builder.error(JavaScriptBundle.message("javascript.parser.message.expected.name.or.lbrace"));
    } else {
      parseBlockOrFunctionBody(BlockType.PACKAGE_OR_CLASS_BODY);
    }
    _package.done(JSStubElementTypes.PACKAGE_STATEMENT);
  }

  private void parseImportStatement() {
    final PsiBuilder.Marker importStatement = builder.mark();
    try {
      builder.advanceLexer();

      final PsiBuilder.Marker nsAssignment = builder.mark();
      if (!myJavaScriptParser.getTypeParser().parseQualifiedTypeName(true)) {
        builder.error(JavaScriptBundle.message("javascript.parser.message.expected.typename.or.*"));
        nsAssignment.drop();
        return;
      }

      if (builder.getTokenType() == JSTokenTypes.EQ) {
        builder.advanceLexer();
        if (!myJavaScriptParser.getTypeParser().parseQualifiedTypeName()) {
          builder.error(JavaScriptBundle.message("javascript.parser.message.expected.typename.or.*"));
        }

        nsAssignment.done(JSStubElementTypes.ASSIGNMENT_EXPRESSION);
      } else {
        nsAssignment.drop();
      }

      checkForSemicolon();
    }
    finally {
      importStatement.done(JSStubElementTypes.IMPORT_STATEMENT);
    }
  }

  @Override
  protected void parseClassOrInterfaceNoMarker(@NotNull PsiBuilder.Marker clazz) {
    final FunctionParser.@Nullable MethodEmptiness methodEmptiness = builder.getUserData(FunctionParser.methodsEmptinessKey);
    try {
      final IElementType tokenType = builder.getTokenType();
      LOG.assertTrue(JSTokenTypes.CLASS_KEYWORD == tokenType || JSTokenTypes.INTERFACE_KEYWORD == tokenType);
      if (builder.getTokenType() == JSTokenTypes.INTERFACE_KEYWORD) {
        builder.putUserData(FunctionParser.methodsEmptinessKey, FunctionParser.MethodEmptiness.ALWAYS);
        builder.putUserData(withinInterfaceKey, "");
      }

      builder.advanceLexer();

      if (isIdentifierToken(builder.getTokenType())) {
        parsePossiblyQualifiedName();
      }
      else {
        builder.error(JavaScriptBundle.message("javascript.parser.message.expected.typename.or.*"));
      }

      if (builder.getTokenType() == JSTokenTypes.EXTENDS_KEYWORD) {
        parseReferenceList(JSStubElementTypes.EXTENDS_LIST);
      }

      if (builder.getTokenType() == JSTokenTypes.IMPLEMENTS_KEYWORD) {
        parseReferenceList(JSStubElementTypes.IMPLEMENTS_LIST);
      }

      parseBlockOrFunctionBody(BlockType.PACKAGE_OR_CLASS_BODY);
      clazz.done(ActionScriptElementTypes.ACTIONSCRIPT_CLASS);
      clazz.setCustomEdgeTokenBinders(INCLUDE_DOC_COMMENT_AT_LEFT, WhitespacesBinders.DEFAULT_RIGHT_BINDER);
    }
    finally {
      builder.putUserData(FunctionParser.methodsEmptinessKey, methodEmptiness);
      builder.putUserData(withinInterfaceKey, null);
    }
  }

  public void parsePossiblyQualifiedName() {
    boolean validQualifier = JSKeywordSets.IDENTIFIER_NAMES.contains(builder.getTokenType());
    PsiBuilder.Marker qualifier = builder.mark();
    builder.advanceLexer();

    // ECMAScriptLevelFourParsingTest.testOldAs2Code
    while (validQualifier && builder.getTokenType() == JSTokenTypes.DOT) {
      qualifier.done(JSElementTypes.REFERENCE_EXPRESSION);
      qualifier = qualifier.precede();
      builder.advanceLexer();
      validQualifier = JSKeywordSets.IDENTIFIER_NAMES.contains(builder.getTokenType());
      if (!validQualifier) {
        builder.error(JavaScriptBundle.message("javascript.parser.message.expected.identifier"));
      }
      builder.advanceLexer();
    }
    qualifier.drop();
  }

  @Override
  protected IElementType getClassElementType() {
    return ActionScriptElementTypes.ACTIONSCRIPT_CLASS;
  }

  @Override
  protected IElementType getClassExtendListElementType() {
    return JSStubElementTypes.EXTENDS_LIST;
  }

  @Override
  protected boolean parseDialectSpecificSourceElements(PsiBuilder.Marker marker) {
    IElementType tokenType = builder.getTokenType();
    if (tokenType == JSTokenTypes.LBRACE) {
      parseBlockOrFunctionBody(BlockType.PACKAGE_OR_CLASS_BODY);
      marker.done(ActionScriptElementTypes.CONDITIONAL_COMPILE_BLOCK_STATEMENT);
      return true;
    }
    else if (tokenType == JSTokenTypes.NAMESPACE_KEYWORD && isECMAL4()) {
      if (!parseNamespaceNoMarker(marker)) {
        builder.advanceLexer();
      }
      return true;
    }
    return false;
  }
}
