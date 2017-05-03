package utils;

import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFormatterRegex {
    
    public static final String REGEX_VAR = "^[a-zA-Z]([a-zA-Z0-9_])+?$";
    
    public static final String REGEX_PARAMETER_VALUE = "^[a-zA-Z]([a-zA-Z0-9_])+?$";
    
    public static void makeFormatter(JTextField jTextField, final String regex) {
        jTextField.setDocument( new PlainDocument() {

        private final Pattern pattern = Pattern.compile( regex );

        @Override
        public void insertString( int offs, String str, AttributeSet a ) throws BadLocationException
        {           
            if ( str != null ) {    
                String text = getText( 0, offs ) + str;

                if ( pattern.matcher( text ).matches() ) {
                    super.insertString( offs, str, a );
                }
            }
        }
    });
    }
    public static void ___func_2_teste___(String alou) {
        
    }
}
