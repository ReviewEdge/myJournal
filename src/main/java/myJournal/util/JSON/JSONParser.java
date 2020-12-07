package myJournal.util.JSON;

import java.util.function.Function;

public class JSONParser {
    public static JSONElement parse(String s) {
        System.out.println("started parsing");
        StringBuilder substr = new StringBuilder();
        int len = s.length();
        boolean foundStart = false;
        boolean isObject = false, isArray = false, isString = false, isInt = false;
        Function<Character, Boolean> terminatingCheck = x -> true;
        boolean escaped = false;
        JSONBuilder jb = JSONBuilder.object();
        String key = null;
        boolean foundKey = false;
        boolean foundValue = false;
        boolean inString = false;
        int nestedObjectLevel = 0, nestedArrayLevel = 0;
        JSONElement value = null;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(!foundStart) {
                if(c == '"') {
                    foundStart = true;
                    isString = true;
                }
                else if(Character.isDigit(c)) {
                    substr.append(c);
                    foundStart = true;
                    isInt = true;
                }
                else if(c == '[') {
                    jb = JSONBuilder.array();
                    isArray = true;
                    foundStart = true;
                }
                else if(c == '{') {
                    jb = JSONBuilder.object();
                    isObject = true;
                    foundStart = true;
                }
            }
            else {
                try {
                    if (escaped || !(
                            (isObject && nestedObjectLevel == 0 && c == '}')
                            || (isArray && nestedArrayLevel == 0 && c == ']')
                            || (isInt && !Character.isDigit(c))
                            || (isString && c == '"')
                            )
                    ) {
                        if (isObject) {
                            System.out.println(c);
                            if (key == null) {
                                if (!(c == '"' && !escaped) && foundKey && !Character.isWhitespace(c)) {
                                    substr.append(c);
                                } else if (c == '"' && !escaped && foundKey) {
                                    System.out.println("Ended key");
                                    key = substr.toString();
                                    System.out.println("key = " + key);
                                    substr = new StringBuilder();
                                    foundKey = false;
                                } else if (c == '"') {
                                    System.out.println("Started key");
                                    foundKey = true;
                                }
                            } else if (value == null) {
                                if(c == ':' & !escaped && !foundValue) {
                                    System.out.println("Started value");
                                    foundValue = true;
                                } else if (c == ',' && foundValue && !inString && nestedArrayLevel == 0 && nestedObjectLevel == 0) {
                                    value = JSONParser.parse(substr.toString());
                                    substr = new StringBuilder();
                                    jb.pair(key, value);
                                    foundKey = false;
                                    foundValue = false;
                                    key = null;
                                    value = null;
                                } else {
                                    substr.append(c);
                                }
                            }
                        } else if (isArray) {
                            if (value == null) {
                                if (c == ',' && !inString && nestedArrayLevel == 0 && nestedObjectLevel == 0) {
                                    value = JSONParser.parse(substr.toString());
                                    substr = new StringBuilder();
                                    jb.add(value);
                                    value = null;
                                } else {
                                    substr.append(c);
                                }
                            }
                        } else if (isString) {
                            substr.append(c);
                        } else if (isInt) {
                            substr.append(c);
                        }
                        if(!inString && !escaped) {
                            if(c == '{') nestedObjectLevel ++;
                            if(c == '}') nestedObjectLevel --;
                            if(c == '[') nestedArrayLevel ++;
                            if(c == ']') nestedArrayLevel --;
                        }
                    } else {
                        if (isArray || isObject) {
                            if(substr.toString().trim().length() > 0) {
                                System.out.println("Parsing" + substr.toString());
                                if(isObject) jb.pair(key, JSONParser.parse(substr.toString()));
                                else jb.add(JSONParser.parse(substr.toString()));
                            }
                            return jb.toJSONElement();
                        }
                        else {
                            if (isString) {
                                return JSONValue.from(substr.toString());
                            } else if (isInt) {
                                return JSONValue.from(Long.parseLong(substr.toString()));
                            }
                        }
                    }
                }
                catch(NullPointerException e) {
                    throw new IllegalArgumentException("Incompatible JSON");
                }
            }
            if(c == '"' && !escaped) {
                inString = !inString;
            }
            escaped = false;
            if(c == '\\' && !escaped) {
                escaped = true;
            }
        }
        try {
            if (isInt) return JSONValue.from(Integer.parseInt(substr.toString()));
        }
        catch(NullPointerException | NumberFormatException e) {
            throw new IllegalArgumentException("Incompatible JSON");
        }
        throw new IllegalArgumentException("Incompatible JSON");
    }
}
