package assignment7;

import java.util.*;

public class submission {

    private String name;
    private Map<String, Set<String>> map;

    public submission(String name, ArrayList<String> words,int n){
        map= new HashMap<String, Set<String>>(words.size()*2,(float) 0.75);
        this.name=name;

        for(int i=0;i<words.size()-7;i++){

            if (!map.containsKey(words.get(i))) {
                map.put(words.get(i), new HashSet<String>(100, (float) 0.75));
            }
            Set<String> values = map.get(words.get(i));
            String temp="";

            for(int j=0;j<n;j++)
                temp+=words.get(i+j);

            values.add(temp);

        }
    }

    public Map<String, Set<String>> getMap() {
        return map;
    }




    public int comparasion(submission s2){

        Map<String, Set<String>> m2= s2.getMap();

        int same = 0;
        for(String s : this.map.keySet()){
            if(m2.containsKey(s)){
                for(String j : this.map.get(s)){
                    if(m2.get(s).contains(j)) {
                        same++;
                    }
                }
            }
        }
        return same;
    }



}
