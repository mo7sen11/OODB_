package social;
import java.time.LocalDate;  
import javax.persistence.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
        EntityManager em = emf.createEntityManager();

        try {
            // Correct query
            TypedQuery<user> query = em.createQuery(
                "SELECT DISTINCT u FROM user u LEFT JOIN FETCH u.posts LEFT JOIN FETCH u.posts.comments",
                user.class
            );
            List<user> users = query.getResultList();

            if (users == null || users.isEmpty()) {
                System.out.println("No users found.");
                return;
            }

            for (user u : users) {
                if (u == null) {
                    System.out.println("Encountered a null user entry. Skipping...");
                    continue;
                }

                System.out.println("User: " + u.getName());
                System.out.println("Email: " + u.getEmail());
                System.out.println("Posts:");

                for (Post post : u.getPosts()) {
                    System.out.println("  Post: " + post.getDescription());
                    System.out.println("  Comments:");
                    
                    for (Comment comment : post.getComments()) {
                        System.out.println("    Comment: " + comment.getCommentMessage());
                        System.out.println("    Date: " + comment.getDate());
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close EntityManager and EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}
