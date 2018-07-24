package chapter11;

import java.util.Set;

/**
 * 降低锁访问频率
 */
public class ServerStatus {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatus(Set<String> users, Set<String> queries) {
        this.users = users;
        this.queries = queries;
    }

    public void addUser(String u){
        synchronized (users){
            users.add(u);
        }
    }

    public void addQuery(String q){
        synchronized (queries){
            queries.add(q);
        }
    }
}
