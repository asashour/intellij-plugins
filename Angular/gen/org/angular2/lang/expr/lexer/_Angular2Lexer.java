// Generated by JFlex 1.9.1 http://jflex.de/  (tweaked for IntelliJ platform)
// source: Angular2.flex

package org.angular2.lang.expr.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;

import org.angular2.codeInsight.blocks.Angular2HtmlBlockUtilsKt;

import static org.angular2.lang.expr.lexer.Angular2TokenTypes.*;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;


class _Angular2Lexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int YYEXPRESSION = 2;
  public static final int YYSTRING = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2, 2
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\1\u0200\1\u0300\1\u0400\1\u0500\1\u0600\1\u0700"+
    "\1\u0800\1\u0900\1\u0a00\1\u0b00\1\u0c00\1\u0d00\1\u0e00\1\u0f00"+
    "\1\u1000\1\u0100\1\u1100\1\u1200\1\u1300\1\u0100\1\u1400\1\u1500"+
    "\1\u1600\1\u1700\1\u1800\1\u1900\1\u1a00\1\u1b00\1\u0100\1\u1c00"+
    "\1\u1d00\1\u1e00\12\u1f00\1\u2000\1\u2100\1\u2200\1\u1f00\1\u2300"+
    "\1\u2400\2\u1f00\31\u0100\1\u2500\121\u0100\1\u2600\4\u0100\1\u2700"+
    "\1\u0100\1\u2800\1\u2900\1\u2a00\1\u2b00\1\u2c00\1\u2d00\53\u0100"+
    "\1\u2e00\41\u1f00\1\u0100\1\u2f00\1\u3000\1\u0100\1\u3100\1\u3200"+
    "\1\u3300\1\u3400\1\u1f00\1\u3500\1\u3600\1\u3700\1\u3800\1\u0100"+
    "\1\u3900\1\u3a00\1\u3b00\1\u3c00\1\u3d00\1\u3e00\1\u3f00\1\u1f00"+
    "\1\u4000\1\u4100\1\u4200\1\u4300\1\u4400\1\u4500\1\u4600\1\u4700"+
    "\1\u4800\1\u4900\1\u4a00\1\u4b00\1\u1f00\1\u4c00\1\u4d00\1\u4e00"+
    "\1\u1f00\3\u0100\1\u4f00\1\u5000\1\u5100\12\u1f00\4\u0100\1\u5200"+
    "\17\u1f00\2\u0100\1\u5300\41\u1f00\2\u0100\1\u5400\1\u5500\2\u1f00"+
    "\1\u5600\1\u5700\27\u0100\1\u5800\2\u0100\1\u5900\45\u1f00\1\u0100"+
    "\1\u5a00\1\u5b00\11\u1f00\1\u5c00\27\u1f00\1\u5d00\1\u5e00\1\u5f00"+
    "\1\u6000\11\u1f00\1\u6100\1\u6200\5\u1f00\1\u6300\1\u6400\4\u1f00"+
    "\1\u6500\21\u1f00\246\u0100\1\u6600\20\u0100\1\u6700\1\u6800\25\u0100"+
    "\1\u6900\34\u0100\1\u6a00\14\u1f00\2\u0100\1\u6b00\u0e05\u1f00";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\1\1\0\1\3\22\0\1\1"+
    "\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\12\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\0\4\32"+
    "\1\33\1\32\21\34\1\35\2\34\1\36\1\37\1\40"+
    "\1\41\1\34\1\0\1\42\1\32\1\43\1\44\1\45"+
    "\1\46\1\34\1\47\1\50\2\34\1\51\1\34\1\52"+
    "\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62"+
    "\1\34\1\35\2\34\1\63\1\64\1\65\42\0\1\1"+
    "\11\0\1\66\12\0\1\66\4\0\1\66\5\0\27\66"+
    "\1\0\37\66\1\0\u01ca\66\4\0\14\66\16\0\5\66"+
    "\7\0\1\66\1\0\1\66\201\0\5\66\1\0\2\66"+
    "\2\0\4\66\1\0\1\66\6\0\1\66\1\0\3\66"+
    "\1\0\1\66\1\0\24\66\1\0\123\66\1\0\213\66"+
    "\10\0\246\66\1\0\46\66\2\0\1\66\6\0\51\66"+
    "\107\0\33\66\4\0\4\66\55\0\53\66\43\0\2\66"+
    "\1\0\143\66\1\0\1\66\17\0\2\66\7\0\2\66"+
    "\12\0\3\66\2\0\1\66\20\0\1\66\1\0\36\66"+
    "\35\0\131\66\13\0\1\66\30\0\41\66\11\0\2\66"+
    "\4\0\1\66\5\0\26\66\4\0\1\66\11\0\1\66"+
    "\3\0\1\66\27\0\31\66\7\0\13\66\65\0\25\66"+
    "\1\0\10\66\106\0\66\66\3\0\1\66\22\0\1\66"+
    "\7\0\12\66\17\0\20\66\4\0\10\66\2\0\2\66"+
    "\2\0\26\66\1\0\7\66\1\0\1\66\3\0\4\66"+
    "\3\0\1\66\20\0\1\66\15\0\2\66\1\0\3\66"+
    "\16\0\2\66\12\0\1\66\10\0\6\66\4\0\2\66"+
    "\2\0\26\66\1\0\7\66\1\0\2\66\1\0\2\66"+
    "\1\0\2\66\37\0\4\66\1\0\1\66\23\0\3\66"+
    "\20\0\11\66\1\0\3\66\1\0\26\66\1\0\7\66"+
    "\1\0\2\66\1\0\5\66\3\0\1\66\22\0\1\66"+
    "\17\0\2\66\27\0\1\66\13\0\10\66\2\0\2\66"+
    "\2\0\26\66\1\0\7\66\1\0\2\66\1\0\5\66"+
    "\3\0\1\66\36\0\2\66\1\0\3\66\17\0\1\66"+
    "\21\0\1\66\1\0\6\66\3\0\3\66\1\0\4\66"+
    "\3\0\2\66\1\0\1\66\1\0\2\66\3\0\2\66"+
    "\3\0\3\66\3\0\14\66\26\0\1\66\64\0\10\66"+
    "\1\0\3\66\1\0\27\66\1\0\20\66\3\0\1\66"+
    "\32\0\3\66\5\0\2\66\36\0\1\66\4\0\10\66"+
    "\1\0\3\66\1\0\27\66\1\0\12\66\1\0\5\66"+
    "\3\0\1\66\40\0\1\66\1\0\2\66\17\0\2\66"+
    "\22\0\10\66\1\0\3\66\1\0\51\66\2\0\1\66"+
    "\20\0\1\66\5\0\3\66\10\0\3\66\30\0\6\66"+
    "\5\0\22\66\3\0\30\66\1\0\11\66\1\0\1\66"+
    "\2\0\7\66\72\0\60\66\1\0\2\66\14\0\7\66"+
    "\72\0\2\66\1\0\1\66\1\0\5\66\1\0\30\66"+
    "\1\0\1\66\1\0\12\66\1\0\2\66\11\0\1\66"+
    "\2\0\5\66\1\0\1\66\25\0\4\66\40\0\1\66"+
    "\77\0\10\66\1\0\44\66\33\0\5\66\163\0\53\66"+
    "\24\0\1\66\20\0\6\66\4\0\4\66\3\0\1\66"+
    "\3\0\2\66\7\0\3\66\4\0\15\66\14\0\1\66"+
    "\21\0\46\66\1\0\1\66\5\0\1\66\2\0\53\66"+
    "\1\0\115\66\1\0\4\66\2\0\7\66\1\0\1\66"+
    "\1\0\4\66\2\0\51\66\1\0\4\66\2\0\41\66"+
    "\1\0\4\66\2\0\7\66\1\0\1\66\1\0\4\66"+
    "\2\0\17\66\1\0\71\66\1\0\4\66\2\0\103\66"+
    "\45\0\20\66\20\0\126\66\2\0\6\66\3\0\u016c\66"+
    "\2\0\21\66\1\0\32\66\5\0\113\66\6\0\10\66"+
    "\7\0\15\66\1\0\4\66\16\0\22\66\16\0\22\66"+
    "\16\0\15\66\1\0\3\66\17\0\64\66\43\0\1\66"+
    "\4\0\1\66\103\0\131\66\7\0\5\66\2\0\42\66"+
    "\1\0\1\66\5\0\106\66\12\0\37\66\61\0\36\66"+
    "\2\0\5\66\13\0\54\66\4\0\32\66\66\0\27\66"+
    "\11\0\65\66\122\0\1\66\135\0\57\66\21\0\7\66"+
    "\67\0\36\66\15\0\2\66\12\0\54\66\32\0\44\66"+
    "\51\0\3\66\12\0\44\66\2\0\11\66\7\0\53\66"+
    "\2\0\3\66\51\0\4\66\1\0\6\66\1\0\2\66"+
    "\3\0\1\66\5\0\300\66\100\0\26\66\2\0\6\66"+
    "\2\0\46\66\2\0\6\66\2\0\10\66\1\0\1\66"+
    "\1\0\1\66\1\0\1\66\1\0\37\66\2\0\65\66"+
    "\1\0\7\66\1\0\1\66\3\0\3\66\1\0\7\66"+
    "\3\0\4\66\2\0\6\66\4\0\15\66\5\0\3\66"+
    "\1\0\7\66\164\0\1\66\15\0\1\66\20\0\15\66"+
    "\145\0\1\66\4\0\1\66\2\0\12\66\1\0\1\66"+
    "\3\0\5\66\6\0\1\66\1\0\1\66\1\0\1\66"+
    "\1\0\4\66\1\0\13\66\2\0\4\66\5\0\5\66"+
    "\4\0\1\66\64\0\2\66\u017b\0\57\66\1\0\57\66"+
    "\1\0\205\66\6\0\4\66\3\0\2\66\14\0\46\66"+
    "\1\0\1\66\5\0\1\66\2\0\70\66\7\0\1\66"+
    "\20\0\27\66\11\0\7\66\1\0\7\66\1\0\7\66"+
    "\1\0\7\66\1\0\7\66\1\0\7\66\1\0\7\66"+
    "\1\0\7\66\120\0\1\66\325\0\2\66\52\0\5\66"+
    "\5\0\2\66\4\0\126\66\6\0\3\66\1\0\132\66"+
    "\1\0\4\66\5\0\53\66\1\0\136\66\21\0\33\66"+
    "\65\0\306\66\112\0\360\66\20\0\215\66\103\0\56\66"+
    "\2\0\15\66\3\0\20\66\12\0\2\66\24\0\57\66"+
    "\20\0\37\66\2\0\106\66\61\0\11\66\2\0\147\66"+
    "\2\0\65\66\2\0\5\66\60\0\13\66\1\0\3\66"+
    "\1\0\4\66\1\0\27\66\35\0\64\66\16\0\62\66"+
    "\76\0\6\66\3\0\1\66\1\0\2\66\13\0\34\66"+
    "\12\0\27\66\31\0\35\66\7\0\57\66\34\0\1\66"+
    "\20\0\5\66\1\0\12\66\12\0\5\66\1\0\51\66"+
    "\27\0\3\66\1\0\10\66\24\0\27\66\3\0\1\66"+
    "\3\0\62\66\1\0\1\66\3\0\2\66\2\0\5\66"+
    "\2\0\1\66\1\0\1\66\30\0\3\66\2\0\13\66"+
    "\7\0\3\66\14\0\6\66\2\0\6\66\2\0\6\66"+
    "\11\0\7\66\1\0\7\66\1\0\53\66\1\0\14\66"+
    "\10\0\163\66\35\0\244\66\14\0\27\66\4\0\61\66"+
    "\4\0\156\66\2\0\152\66\46\0\7\66\14\0\5\66"+
    "\5\0\1\66\1\0\12\66\1\0\15\66\1\0\5\66"+
    "\1\0\1\66\1\0\2\66\1\0\2\66\1\0\154\66"+
    "\41\0\153\66\22\0\100\66\2\0\66\66\50\0\14\66"+
    "\164\0\5\66\1\0\207\66\44\0\32\66\6\0\32\66"+
    "\13\0\131\66\3\0\6\66\2\0\6\66\2\0\6\66"+
    "\2\0\3\66\43\0\14\66\1\0\32\66\1\0\23\66"+
    "\1\0\2\66\1\0\17\66\2\0\16\66\42\0\173\66"+
    "\205\0\35\66\3\0\61\66\57\0\40\66\15\0\24\66"+
    "\1\0\10\66\6\0\46\66\12\0\36\66\2\0\44\66"+
    "\4\0\10\66\60\0\236\66\22\0\44\66\4\0\44\66"+
    "\4\0\50\66\10\0\64\66\234\0\67\66\11\0\26\66"+
    "\12\0\10\66\230\0\6\66\2\0\1\66\1\0\54\66"+
    "\1\0\2\66\3\0\1\66\2\0\27\66\12\0\27\66"+
    "\11\0\37\66\101\0\23\66\1\0\2\66\12\0\26\66"+
    "\12\0\32\66\106\0\70\66\6\0\2\66\100\0\1\66"+
    "\17\0\4\66\1\0\3\66\1\0\35\66\52\0\35\66"+
    "\3\0\35\66\43\0\10\66\1\0\34\66\33\0\66\66"+
    "\12\0\26\66\12\0\23\66\15\0\22\66\156\0\111\66"+
    "\67\0\63\66\15\0\63\66\15\0\44\66\334\0\35\66"+
    "\12\0\1\66\10\0\26\66\232\0\27\66\14\0\65\66"+
    "\113\0\55\66\40\0\31\66\32\0\44\66\35\0\1\66"+
    "\13\0\43\66\3\0\1\66\14\0\60\66\16\0\4\66"+
    "\25\0\1\66\1\0\1\66\43\0\22\66\1\0\31\66"+
    "\124\0\7\66\1\0\1\66\1\0\4\66\1\0\17\66"+
    "\1\0\12\66\7\0\57\66\46\0\10\66\2\0\2\66"+
    "\2\0\26\66\1\0\7\66\1\0\2\66\1\0\5\66"+
    "\3\0\1\66\22\0\1\66\14\0\5\66\236\0\65\66"+
    "\22\0\4\66\24\0\1\66\40\0\60\66\24\0\2\66"+
    "\1\0\1\66\270\0\57\66\51\0\4\66\44\0\60\66"+
    "\24\0\1\66\73\0\53\66\15\0\1\66\107\0\33\66"+
    "\345\0\54\66\164\0\100\66\37\0\1\66\240\0\10\66"+
    "\2\0\47\66\20\0\1\66\1\0\1\66\34\0\1\66"+
    "\12\0\50\66\7\0\1\66\25\0\1\66\13\0\56\66"+
    "\23\0\1\66\42\0\71\66\7\0\11\66\1\0\45\66"+
    "\21\0\1\66\61\0\36\66\160\0\7\66\1\0\2\66"+
    "\1\0\46\66\25\0\1\66\31\0\6\66\1\0\2\66"+
    "\1\0\40\66\16\0\1\66\u0147\0\23\66\15\0\232\66"+
    "\346\0\304\66\274\0\57\66\321\0\107\66\271\0\71\66"+
    "\7\0\37\66\161\0\36\66\22\0\60\66\20\0\4\66"+
    "\37\0\25\66\5\0\23\66\260\0\100\66\200\0\113\66"+
    "\5\0\1\66\102\0\15\66\100\0\2\66\1\0\1\66"+
    "\34\0\370\66\10\0\363\66\15\0\37\66\61\0\3\66"+
    "\21\0\4\66\10\0\u018c\66\4\0\153\66\5\0\15\66"+
    "\3\0\11\66\7\0\12\66\146\0\125\66\1\0\107\66"+
    "\1\0\2\66\2\0\1\66\2\0\2\66\2\0\4\66"+
    "\1\0\14\66\1\0\1\66\1\0\7\66\1\0\101\66"+
    "\1\0\4\66\2\0\10\66\1\0\7\66\1\0\34\66"+
    "\1\0\4\66\1\0\5\66\1\0\1\66\3\0\7\66"+
    "\1\0\u0154\66\2\0\31\66\1\0\31\66\1\0\37\66"+
    "\1\0\31\66\1\0\37\66\1\0\31\66\1\0\37\66"+
    "\1\0\31\66\1\0\37\66\1\0\31\66\1\0\10\66"+
    "\64\0\55\66\12\0\7\66\20\0\1\66\u0171\0\54\66"+
    "\24\0\305\66\73\0\104\66\7\0\1\66\264\0\4\66"+
    "\1\0\33\66\1\0\2\66\1\0\1\66\2\0\1\66"+
    "\1\0\12\66\1\0\4\66\1\0\1\66\1\0\1\66"+
    "\6\0\1\66\4\0\1\66\1\0\1\66\1\0\1\66"+
    "\1\0\3\66\1\0\2\66\1\0\1\66\2\0\1\66"+
    "\1\0\1\66\1\0\1\66\1\0\1\66\1\0\1\66"+
    "\1\0\2\66\1\0\1\66\2\0\4\66\1\0\7\66"+
    "\1\0\4\66\1\0\4\66\1\0\1\66\1\0\12\66"+
    "\1\0\21\66\5\0\3\66\1\0\5\66\1\0\21\66"+
    "\104\0\327\66\51\0\65\66\13\0\336\66\2\0\u0182\66"+
    "\16\0\u0131\66\37\0\36\66\342\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[27648];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\1\1\3\1\4\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\4\1\34"+
    "\1\35\11\10\1\36\1\37\1\40\1\41\1\42\1\43"+
    "\1\41\1\44\1\42\1\0\1\3\1\45\1\46\2\0"+
    "\1\24\1\47\1\24\1\50\1\51\1\52\1\53\1\54"+
    "\1\55\2\10\1\56\6\10\1\57\4\0\2\60\1\0"+
    "\1\3\1\61\2\0\1\24\1\62\1\63\2\10\1\64"+
    "\4\10\1\65\2\0\1\66\4\0\1\67\1\70\1\3"+
    "\2\0\1\71\1\10\1\72\1\73\1\74\1\10\5\0"+
    "\1\70\1\3\2\0\1\75\1\10\3\0\1\70\1\3"+
    "\1\76\1\77\1\10\1\100\1\101\1\3\1\10\1\102"+
    "\1\10\1\103";

  private static int [] zzUnpackAction() {
    int [] result = new int[144];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\67\0\156\0\245\0\334\0\u0113\0\u014a\0\u0181"+
    "\0\245\0\u01b8\0\245\0\245\0\u01ef\0\245\0\u0226\0\245"+
    "\0\245\0\245\0\245\0\245\0\245\0\245\0\u025d\0\u0294"+
    "\0\u02cb\0\245\0\245\0\u0302\0\u0339\0\u0370\0\u03a7\0\245"+
    "\0\u014a\0\245\0\245\0\u03de\0\u0415\0\u044c\0\u0483\0\u04ba"+
    "\0\u04f1\0\u0528\0\u055f\0\u0596\0\245\0\u05cd\0\245\0\u0604"+
    "\0\245\0\245\0\u063b\0\245\0\u0672\0\u014a\0\u06a9\0\u06e0"+
    "\0\245\0\u0717\0\u074e\0\u0785\0\u07bc\0\u07f3\0\245\0\u082a"+
    "\0\245\0\245\0\245\0\u0861\0\u0898\0\u08cf\0\u01ef\0\u0906"+
    "\0\u093d\0\u0974\0\u09ab\0\u09e2\0\u0a19\0\245\0\u0a50\0\u0a87"+
    "\0\u0abe\0\u0af5\0\245\0\u0b2c\0\u0b63\0\u0b9a\0\245\0\u0bd1"+
    "\0\u0c08\0\u0c3f\0\245\0\245\0\u0c76\0\u0cad\0\u01ef\0\u0ce4"+
    "\0\u0d1b\0\u0d52\0\u0d89\0\u01ef\0\u0dc0\0\u0df7\0\245\0\u0e2e"+
    "\0\u0e65\0\u0e9c\0\u0ed3\0\245\0\u0f0a\0\u0f41\0\u0f78\0\u0faf"+
    "\0\u01ef\0\u0fe6\0\u01ef\0\u01ef\0\u01ef\0\u101d\0\u1054\0\u108b"+
    "\0\u10c2\0\u10f9\0\u1130\0\u1167\0\u119e\0\u11d5\0\u120c\0\u01ef"+
    "\0\u1243\0\u127a\0\u12b1\0\u12e8\0\u131f\0\u1356\0\245\0\245"+
    "\0\u138d\0\245\0\245\0\u13c4\0\u13fb\0\u0113\0\u1432\0\u01ef";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[144];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\3\5\26\4\4\6\1\4\1\7\2\4\12\6"+
    "\1\10\6\6\4\4\1\11\3\5\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\4\15\1\40\1\41\1\42\1\43"+
    "\1\44\2\15\1\45\1\46\1\15\1\47\1\50\1\51"+
    "\5\15\1\52\1\53\1\54\1\55\1\56\1\57\1\11"+
    "\2\60\2\61\1\60\1\62\3\60\1\63\1\64\24\60"+
    "\1\65\27\60\70\0\3\5\33\0\1\66\61\0\4\6"+
    "\4\0\21\6\6\0\1\5\116\0\4\6\4\0\14\6"+
    "\1\67\4\6\33\0\1\70\46\0\1\15\13\0\1\15"+
    "\6\0\4\15\4\0\21\15\15\0\1\71\30\0\1\72"+
    "\12\0\1\73\34\0\1\74\65\0\1\75\65\0\1\74"+
    "\1\0\1\31\7\0\1\76\11\0\1\76\50\0\1\77"+
    "\66\0\1\100\66\0\1\101\60\0\1\102\7\0\1\103"+
    "\44\0\1\15\13\0\1\15\6\0\4\15\4\0\15\15"+
    "\1\104\3\15\13\0\1\15\13\0\1\15\6\0\4\15"+
    "\4\0\7\15\1\105\11\15\13\0\1\15\13\0\1\15"+
    "\6\0\4\15\4\0\1\106\20\15\13\0\1\15\13\0"+
    "\1\15\6\0\4\15\4\0\4\15\1\107\14\15\13\0"+
    "\1\15\13\0\1\15\6\0\4\15\4\0\3\15\1\110"+
    "\15\15\13\0\1\15\13\0\1\15\6\0\4\15\4\0"+
    "\17\15\1\111\1\15\13\0\1\15\13\0\1\15\6\0"+
    "\4\15\4\0\5\15\1\112\6\15\1\113\4\15\13\0"+
    "\1\15\13\0\1\15\6\0\4\15\4\0\10\15\1\114"+
    "\10\15\13\0\1\15\13\0\1\15\6\0\4\15\4\0"+
    "\1\115\20\15\70\0\1\116\2\0\2\60\2\0\1\60"+
    "\1\0\3\60\2\0\24\60\1\0\27\60\6\0\1\117"+
    "\15\0\1\120\5\0\4\120\4\0\1\121\12\120\1\122"+
    "\5\120\3\0\1\120\2\123\2\0\5\123\1\124\47\123"+
    "\1\125\5\123\32\0\4\6\4\0\3\6\1\126\15\6"+
    "\33\0\1\127\113\0\1\130\73\0\1\131\30\0\1\74"+
    "\7\0\1\76\11\0\1\76\21\0\67\75\16\0\1\132"+
    "\1\0\1\132\2\0\1\132\72\0\1\133\46\0\1\15"+
    "\11\0\1\134\1\0\1\15\6\0\4\15\4\0\21\15"+
    "\13\0\1\15\13\0\1\15\6\0\4\15\4\0\15\15"+
    "\1\135\3\15\13\0\1\15\13\0\1\15\6\0\4\15"+
    "\4\0\7\15\1\136\11\15\13\0\1\15\13\0\1\15"+
    "\6\0\4\15\4\0\16\15\1\137\2\15\13\0\1\15"+
    "\13\0\1\15\6\0\4\15\4\0\7\15\1\140\11\15"+
    "\13\0\1\15\13\0\1\15\6\0\4\15\4\0\6\15"+
    "\1\141\12\15\13\0\1\15\13\0\1\15\6\0\4\15"+
    "\4\0\17\15\1\142\1\15\13\0\1\15\13\0\1\15"+
    "\6\0\4\15\4\0\2\15\1\143\16\15\13\0\1\15"+
    "\13\0\1\15\6\0\4\15\4\0\14\15\1\144\4\15"+
    "\27\0\1\145\11\0\1\146\51\0\2\120\1\0\2\120"+
    "\1\147\4\0\4\120\4\0\21\120\3\0\1\120\20\0"+
    "\2\120\1\0\2\120\1\147\4\0\4\120\4\0\12\120"+
    "\1\150\6\120\3\0\1\120\20\0\2\120\1\0\2\120"+
    "\1\147\4\0\4\120\4\0\17\120\1\151\1\120\3\0"+
    "\1\120\6\0\1\152\15\0\1\153\5\0\4\153\4\0"+
    "\21\153\3\0\1\153\23\154\1\155\6\154\2\155\6\154"+
    "\5\155\20\154\32\0\4\6\4\0\4\6\1\156\14\6"+
    "\57\0\1\157\66\0\1\160\36\0\1\132\52\0\1\15"+
    "\13\0\1\15\6\0\4\15\4\0\3\15\1\161\15\15"+
    "\13\0\1\15\13\0\1\15\6\0\4\15\4\0\15\15"+
    "\1\162\3\15\13\0\1\15\13\0\1\15\6\0\4\15"+
    "\4\0\7\15\1\163\11\15\13\0\1\15\13\0\1\15"+
    "\6\0\4\15\4\0\15\15\1\164\3\15\13\0\1\15"+
    "\13\0\1\15\6\0\4\15\4\0\3\15\1\165\15\15"+
    "\13\0\1\15\13\0\1\15\6\0\4\15\4\0\3\15"+
    "\1\166\15\15\27\0\1\145\1\0\1\147\64\0\1\167"+
    "\6\0\2\167\6\0\5\167\40\0\2\120\1\0\2\120"+
    "\1\147\4\0\4\120\4\0\11\120\1\170\7\120\3\0"+
    "\1\120\20\0\2\120\1\0\2\120\1\147\4\0\4\120"+
    "\4\0\11\120\1\171\7\120\3\0\1\120\23\0\1\172"+
    "\11\0\1\173\51\0\2\153\1\0\2\153\1\123\4\0"+
    "\4\153\4\0\21\153\3\0\1\153\23\0\1\174\6\0"+
    "\2\174\6\0\5\174\52\0\4\6\4\0\3\6\1\175"+
    "\15\6\63\0\1\176\67\0\1\177\15\0\1\15\13\0"+
    "\1\15\6\0\4\15\4\0\3\15\1\200\15\15\13\0"+
    "\1\15\13\0\1\15\6\0\4\15\4\0\4\15\1\201"+
    "\14\15\27\0\1\167\1\0\1\147\4\0\2\167\6\0"+
    "\5\167\40\0\2\120\1\0\2\120\1\147\4\0\4\120"+
    "\4\0\15\120\1\202\3\120\3\0\1\120\20\0\2\120"+
    "\1\0\2\120\1\147\4\0\4\120\4\0\16\120\1\203"+
    "\2\120\3\0\1\120\23\0\1\172\1\0\1\123\64\0"+
    "\1\204\6\0\2\204\6\0\5\204\43\0\1\205\6\0"+
    "\2\205\6\0\5\205\52\0\4\6\4\0\16\6\1\206"+
    "\2\6\31\0\1\207\66\0\1\210\50\0\1\15\13\0"+
    "\1\15\6\0\4\15\4\0\6\15\1\211\12\15\24\0"+
    "\2\120\1\0\2\120\1\212\4\0\4\120\4\0\21\120"+
    "\3\0\1\120\20\0\2\120\1\0\2\120\1\213\4\0"+
    "\4\120\4\0\21\120\3\0\1\120\23\0\1\204\1\0"+
    "\1\123\4\0\2\204\6\0\5\204\43\0\1\123\6\0"+
    "\2\123\6\0\5\123\52\0\4\6\4\0\1\6\1\214"+
    "\17\6\13\0\1\15\13\0\1\15\6\0\4\15\4\0"+
    "\10\15\1\215\10\15\36\0\4\6\4\0\5\6\1\216"+
    "\13\6\13\0\1\15\13\0\1\15\6\0\4\15\4\0"+
    "\3\15\1\217\15\15\13\0\1\15\13\0\1\15\6\0"+
    "\4\15\4\0\2\15\1\220\16\15\4\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[5225];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\4\1\1\11\1\1\2\11\1\1\1\11"+
    "\1\1\7\11\3\1\2\11\4\1\1\11\1\1\2\11"+
    "\11\1\1\11\1\1\1\11\1\1\2\11\1\1\1\11"+
    "\1\1\1\0\2\1\1\11\2\0\3\1\1\11\1\1"+
    "\3\11\12\1\1\11\4\0\1\11\1\1\1\0\1\1"+
    "\1\11\2\0\1\1\2\11\10\1\2\0\1\11\4\0"+
    "\1\11\2\1\2\0\6\1\5\0\2\1\2\0\2\1"+
    "\3\0\2\1\2\11\1\1\2\11\5\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[144];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  protected int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
  private char quote;

  private String blockName;
  private int blockParamIndex;

  public _Angular2Lexer(Angular2Lexer.Config config) {
    this((java.io.Reader)null);
    if (config instanceof Angular2Lexer.BlockParameter blockParameter) {
      blockName = blockParameter.getName();
      blockParamIndex = blockParameter.getIndex();
    }
  }

  private boolean shouldStartWithParameter() {
    return blockName != null && (blockParamIndex > 0 || !Angular2HtmlBlockUtilsKt.getBLOCKS_WITH_PRIMARY_EXPRESSION().contains(blockName));
  }



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  _Angular2Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { yypushback(1); yybegin(YYEXPRESSION);
            }
          // fall through
          case 68: break;
          case 2:
            { return WHITE_SPACE;
            }
          // fall through
          case 69: break;
          case 3:
            { yybegin(YYEXPRESSION); if (shouldStartWithParameter()) return BLOCK_PARAMETER_NAME; else yypushback(yylength());
            }
          // fall through
          case 70: break;
          case 4:
            { return BAD_CHARACTER;
            }
          // fall through
          case 71: break;
          case 5:
            { return EXCL;
            }
          // fall through
          case 72: break;
          case 6:
            { yybegin(YYSTRING); quote = '"'; return STRING_LITERAL_PART;
            }
          // fall through
          case 73: break;
          case 7:
            { return SHARP;
            }
          // fall through
          case 74: break;
          case 8:
            { return IDENTIFIER;
            }
          // fall through
          case 75: break;
          case 9:
            { return PERC;
            }
          // fall through
          case 76: break;
          case 10:
            { return AND;
            }
          // fall through
          case 77: break;
          case 11:
            { yybegin(YYSTRING); quote = '\''; return STRING_LITERAL_PART;
            }
          // fall through
          case 78: break;
          case 12:
            { return LPAR;
            }
          // fall through
          case 79: break;
          case 13:
            { return RPAR;
            }
          // fall through
          case 80: break;
          case 14:
            { return MULT;
            }
          // fall through
          case 81: break;
          case 15:
            { return PLUS;
            }
          // fall through
          case 82: break;
          case 16:
            { return COMMA;
            }
          // fall through
          case 83: break;
          case 17:
            { return MINUS;
            }
          // fall through
          case 84: break;
          case 18:
            { return DOT;
            }
          // fall through
          case 85: break;
          case 19:
            { return DIV;
            }
          // fall through
          case 86: break;
          case 20:
            { return NUMERIC_LITERAL;
            }
          // fall through
          case 87: break;
          case 21:
            { return COLON;
            }
          // fall through
          case 88: break;
          case 22:
            { return SEMICOLON;
            }
          // fall through
          case 89: break;
          case 23:
            { return LT;
            }
          // fall through
          case 90: break;
          case 24:
            { return EQ;
            }
          // fall through
          case 91: break;
          case 25:
            { return GT;
            }
          // fall through
          case 92: break;
          case 26:
            { return QUEST;
            }
          // fall through
          case 93: break;
          case 27:
            { return LBRACKET;
            }
          // fall through
          case 94: break;
          case 28:
            { return RBRACKET;
            }
          // fall through
          case 95: break;
          case 29:
            { return XOR;
            }
          // fall through
          case 96: break;
          case 30:
            { return LBRACE;
            }
          // fall through
          case 97: break;
          case 31:
            { return OR;
            }
          // fall through
          case 98: break;
          case 32:
            { return RBRACE;
            }
          // fall through
          case 99: break;
          case 33:
            { return STRING_LITERAL_PART;
            }
          // fall through
          case 100: break;
          case 34:
            { yypushback(yytext().length()); yybegin(YYEXPRESSION);
            }
          // fall through
          case 101: break;
          case 35:
            { if (quote == '"') yybegin(YYEXPRESSION); return STRING_LITERAL_PART;
            }
          // fall through
          case 102: break;
          case 36:
            { if (quote == '\'') yybegin(YYEXPRESSION); return STRING_LITERAL_PART;
            }
          // fall through
          case 103: break;
          case 37:
            { return NE;
            }
          // fall through
          case 104: break;
          case 38:
            { return ANDAND;
            }
          // fall through
          case 105: break;
          case 39:
            { return C_STYLE_COMMENT;
            }
          // fall through
          case 106: break;
          case 40:
            { return LE;
            }
          // fall through
          case 107: break;
          case 41:
            { return EQEQ;
            }
          // fall through
          case 108: break;
          case 42:
            { return GE;
            }
          // fall through
          case 109: break;
          case 43:
            { return ELVIS;
            }
          // fall through
          case 110: break;
          case 44:
            { return QUEST_QUEST;
            }
          // fall through
          case 111: break;
          case 45:
            { return AS_KEYWORD;
            }
          // fall through
          case 112: break;
          case 46:
            { return IF_KEYWORD;
            }
          // fall through
          case 113: break;
          case 47:
            { return OROR;
            }
          // fall through
          case 114: break;
          case 48:
            { return ESCAPE_SEQUENCE;
            }
          // fall through
          case 115: break;
          case 49:
            { return NEQEQ;
            }
          // fall through
          case 116: break;
          case 50:
            { return EQEQEQ;
            }
          // fall through
          case 117: break;
          case 51:
            // lookahead expression with fixed base length
            zzMarkedPos = Character.offsetByCodePoints
                (zzBufferL, zzStartRead, 2);
            { return IDENTIFIER;
            }
          // fall through
          case 118: break;
          case 52:
            { return LET_KEYWORD;
            }
          // fall through
          case 119: break;
          case 53:
            { return VAR_KEYWORD;
            }
          // fall through
          case 120: break;
          case 54:
            { return XML_CHAR_ENTITY_REF;
            }
          // fall through
          case 121: break;
          case 55:
            { yypushback(1); return INVALID_ESCAPE_SEQUENCE;
            }
          // fall through
          case 122: break;
          case 56:
            { return INVALID_ESCAPE_SEQUENCE;
            }
          // fall through
          case 123: break;
          case 57:
            { return ELSE_KEYWORD;
            }
          // fall through
          case 124: break;
          case 58:
            { return NULL_KEYWORD;
            }
          // fall through
          case 125: break;
          case 59:
            { return THIS_KEYWORD;
            }
          // fall through
          case 126: break;
          case 60:
            { return TRUE_KEYWORD;
            }
          // fall through
          case 127: break;
          case 61:
            { return FALSE_KEYWORD;
            }
          // fall through
          case 128: break;
          case 62:
            { yybegin(YYSTRING); quote = '\''; return XML_CHAR_ENTITY_REF;
            }
          // fall through
          case 129: break;
          case 63:
            { yybegin(YYSTRING); quote = '"'; return XML_CHAR_ENTITY_REF;
            }
          // fall through
          case 130: break;
          case 64:
            { if (quote == '\'') yybegin(YYEXPRESSION); return XML_CHAR_ENTITY_REF;
            }
          // fall through
          case 131: break;
          case 65:
            { if (quote == '"') yybegin(YYEXPRESSION); return XML_CHAR_ENTITY_REF;
            }
          // fall through
          case 132: break;
          case 66:
            { if (shouldStartWithParameter()) return BLOCK_PARAMETER_NAME; else { yybegin(YYEXPRESSION); yypushback(yylength());}
            }
          // fall through
          case 133: break;
          case 67:
            { return UNDEFINED_KEYWORD;
            }
          // fall through
          case 134: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}