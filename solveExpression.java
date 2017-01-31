package project_1;

import java.lang.*;
import java.util.*;

public class solveExpression {
    private String exp;
    protected int returnType = 0; /* 0, 1, 2 */ 
    protected String ans[] = {"Syntax Error !", "Math Error !", ""};
    
    private StringTokenizer element;
    private List<String> infix, prefix;
    
    protected solveExpression(String exp) {
        this.exp = exp;
        if (!error()) {
            infixToPrefix(); 
            calculatePrefix();
        }
    }
    
    protected String Answer() {
        return ans[returnType];
    }
    
    private boolean error() {
        element = new StringTokenizer(exp, "+-x/()*", true);
        infix = new ArrayList<String>();
        while (element.hasMoreElements()) 
            infix.add(new String(element.nextElement().toString()));
        
        for (int i = 0; i < infix.size(); i++) {
            String s = infix.get(i);
            if (s.equals("+") || s.equals("-") || s.equals("(")) {
                if (i == infix.size()-1) return true;
                String t = infix.get(i+1);
                if (t.equals("x") || t.equals("/") || t.equals(")")) return true;
                if (s.equals("+") || s.equals("-")) {
                    if (t.equals("+")) {
                        infix.remove(i+1);
                        i--;
                    }
                    else if (t.equals("-")) {
                        infix.set(i, s.equals("+") ? "-" : "+");
                        infix.remove(i+1);
                        i--;
                    }
                    else {
                        if (i == 0) {infix.add(i--, "0"); continue;}
                        String g = infix.get(i-1);
                        if (t.equals("(")) {
                            if (g.equals("x") || g.equals("/")) {
                                infix.set(i, s.equals("+") ? "1" : "-1");
                                infix.add(i+1, g);
                                i--;
                            }
                            else if (g.equals("(")) {
                                if (s.equals("+")) infix.remove(i--);
                                else infix.add(i--, "0");
                            }
                            continue;
                        }
                        if (g.equals("(") || g.equals("x") || g.equals("/")) {
                            if (s.equals("+")) infix.remove(i--);
                            else {
                                infix.remove(i);
                                StringBuilder num = new StringBuilder("-");
                                num.append(infix.get(i));
                                infix.set(i, new String(num));
                                i--;
                            }
                        }
                    }
                }
            }
            else if (s.equals("x") || s.equals("/")) {
                if (i == 0 || i == infix.size()-1) return true;
                String t = infix.get(i+1);
                if (t.equals("x") || t.equals("/") || t.equals(")")) return true;
            }
            else if (s.equals(")")) {
                if (i == 0) return true;
                if (i == infix.size()-1) continue;
                String t = infix.get(i+1);
                if (t.equals("+") || t.equals("-") || t.equals("x") || t.equals("/") || t.equals(")")) continue;
                if (t.equals("(")) {
                    infix.add(i+1, "x"); continue;
                }
                return true;
            }
            else {
                try {
                    double t = (double) Double.parseDouble(s);
                } 
                catch (NumberFormatException e) {
                    return true;
                }
                if (i == infix.size()-1) continue;
                String t = infix.get(i+1);
                if (t.equals("(")) infix.add(i+1, "x");
            }
        }
        return checkOpenClose();
    }
    
    private boolean checkOpenClose() {
        int cnt = 0;
        for (String s : infix) {
            if (s.equals("(")) cnt++;
            else if (s.equals(")")) {
                if (cnt == 0) return true;
                cnt--;
            }
        }
        for (; cnt > 0; cnt--) infix.add(")");
        return cnt == 0 ? false : true;
    }
    
    private int rank(String s) {
        if (s.equals("x") || s.equals("/")) return 2;
        if (s.equals("+") || s.equals("-")) return 1;
        return 0;
    }
    
    private void infixToPrefix() {
        Collections.reverse(infix);
        Deque<String> st = new LinkedList<String>();
        prefix = new ArrayList<String>();

        for (String t : infix) {
            if (t.equals("+") || t.equals("-") || t.equals("x") || t.equals("/")) {
                while (!st.isEmpty() && rank(st.peek()) > rank(t)) {
                    prefix.add(st.pop());
                }
                st.push(t);
            }
            else if (t.equals(")")) {
                st.push(t);
            }
            else if (t.equals("(")) {
                while (!st.isEmpty() && !st.peek().equals(")")) {
                    prefix.add(st.pop());
                }
                st.pop();
            }
            else {
                prefix.add(t);
            }
        }
        while (!st.isEmpty()) prefix.add(st.pop());
        Collections.reverse(prefix);
    }
    
    private void calculatePrefix() {
        Collections.reverse(prefix);
        Deque<Double> st = new LinkedList<Double>();
        for (String t : prefix) {
            if (t.equals("+") || t.equals("-") || t.equals("x") || t.equals("/")) {
                double a = st.pop(), b = st.pop();
                if (t.equals("+")) a += b;
                else if (t.equals("-")) a -= b;
                else if (t.equals("x")) a *= b;
                else {
                    if (Math.abs(b) <= Double.MIN_NORMAL) {
                        returnType = 1;
                        return ;
                    }
                    a /= b;
                }
                st.push(a);
            }
            else {
                st.push(Double.parseDouble(t));
            }
        }
        returnType = 2;
        ans[2] = st.pop().toString();
    }
}
