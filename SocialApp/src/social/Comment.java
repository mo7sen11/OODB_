package social;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String commentMessage;

    private LocalDate date;  

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false) 
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private user user;

    // Default constructor
    public Comment() {
        this.date = LocalDate.now(); 
    }

    
    public Comment(String commentMessage) {
        this.commentMessage = commentMessage;
        this.date = LocalDate.now(); 
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public LocalDate getDate() { 
        return date;
    }

    public void setDate(LocalDate date) {  
        this.date = date;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
