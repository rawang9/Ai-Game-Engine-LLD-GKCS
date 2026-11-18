package game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RuleSet<T extends Board> implements Iterable<Rule<T>>{
    Set<Rule<T>> ruleList = new HashSet<>();
    // #block start: only accept rules for this RuleSet's board type
    public void add(Rule<T> boardRule){
        ruleList.add(boardRule);
    }
    // #block end

    @Override
    public Iterator<Rule<T>> iterator(){
        return ruleList.iterator();
    }
    @Override
    public void forEach(Consumer<? super Rule<T>> action){
        ruleList.forEach(action);
    }
    @Override
    public Spliterator<Rule<T>> spliterator(){
        return ruleList.spliterator();
    }
}
