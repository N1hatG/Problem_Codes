import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class simplifier {
    FileIO fileIO;
    Map<String, List<String>> OG;// OG - Original Grammar
    Map<String, String> EG;// EG - Expanded Grammar
    public simplifier(FileIO fileIO){
        this.fileIO = fileIO;
        this.OG = fileIO.getGrammar();
        this.EG = new HashMap<>();
    }

    public String simplify(){
        for(Map.Entry<String, List<String>> entry : OG.entrySet()){
            for(String val : entry.getValue()){
                for(char ch : val.toCharArray()){
                    if(Character.isUpperCase(ch)){
                        String str = "" + ch;
                        expand(OG.get(str), str);
                    }
                }
            }
        }
        String output = "";
        int index = 0;
        for(String val :OG.get("S")){
            for(char ch: val.toCharArray()){
                if(Character.isUpperCase(ch)){
                    String str = "" +ch;
                    output += "(" + EG.get(str) + ")";
                }
                else
                    output += ch;
            }
            if(index < OG.get("S").size() - 1)
                output += "|";
            index ++;
        }
        return "(" + output + ")";

    }

    public Map<String, String> getEG(){
        return EG;
    }
    public void expand(List<String> values, String key) {
        StringBuilder output = new StringBuilder(); // Initialize StringBuilder for current expansion
        
        for (String val : values) {
            StringBuilder exVal = new StringBuilder(); // Initialize StringBuilder for each value in the list
            for (char ch : val.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    String str = String.valueOf(ch);
                    if (EG.get(str) == null) {
                        expand(OG.get(str), str);
                    }
                    exVal.append("(").append(EG.get(str)).append(")"); // Enclose expanded value in parentheses
                } else {
                    exVal.append(ch);                
                }
            }
            output.append(exVal.toString()); // Append expanded value for the current key to output StringBuilder
            output.append("|"); // Append '|' after each expansion
        }
        
        // Remove the last '|' character
        output.deleteCharAt(output.length() - 1);
        
        // Put the result into the expanded grammar map
        EG.put(key, output.toString());
    }    
}
