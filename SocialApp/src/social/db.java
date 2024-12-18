package social;
import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;

public class db {

	// add
	public static void adduser(user u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static void addcomment(Comment c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static void addpost(Post p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	// get by id
	public static user getuserbyid(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		user u = em.find(user.class, id);
		em.close();
		emf.close();
		return u;
	}

	public static Comment getcommentbyid(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		Comment c = em.find(Comment.class, id);
		em.close();
		emf.close();
		return c;
	}

	public static Post getpostbyid(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		Post p = em.find(Post.class, id);
		em.close();
		emf.close();
		return p;
	}

	// update

	public static void updateUser(int id, String newName, String newEmail, String newpassword, LocalDate newbirthdate) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		user u = em.find(user.class, id);

		try {
			if (u != null) {
				if (newName != null) {
					em.getTransaction().begin();
					u.setName(newName);
					em.getTransaction().commit();
					if (newEmail != null) {
						em.getTransaction().begin();
						u.setEmail(newEmail);
						em.getTransaction().commit();
						if (newpassword != null) {
							em.getTransaction().begin();
							u.setPassword(newpassword);
							em.getTransaction().commit();
							if (newbirthdate != null) {
								em.getTransaction().begin();
								u.setBirthdate(newbirthdate);
								em.getTransaction().commit();
							}
						}
					}
				}
			}
		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();

			}
			e.printStackTrace();

		} finally {
			em.close();
			emf.close();

		}

	}

	public static void updatePost(int id, String newdescription) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		Post p = em.find(Post.class, id);

		try {
			if (p != null) {
				if (newdescription != null) {
					em.getTransaction().begin();
					p.setDescription(newdescription);
					em.getTransaction().commit();

				}
			}
		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();

			}
			e.printStackTrace();

		} finally {
			em.close();
			emf.close();

		}

	}

	public static void updateComment(int id, String newcommentmessage) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		Comment c = em.find(Comment.class, id);

		try {
			if (c != null) {
				if (newcommentmessage != null) {
					em.getTransaction().begin();
					c.setCommentMessage(newcommentmessage);
					em.getTransaction().commit();

				}
			}
		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();

			}
			e.printStackTrace();

		} finally {
			em.close();
			emf.close();

		}

	}

	// delete

	public static void deleteUser(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		user u = em.find(user.class, id);
		if (u != null) {
			em.getTransaction().begin();
			em.remove(u);
			System.out.printf("SUCCESS: user with id: \"%d\" deleted successfully.", id);
			em.getTransaction().commit();
		} else {

			System.out.printf("ERROR: There's no user with id : \"%d\" in the database.", id);
		}
		em.close();
		emf.close();

	}

	public static void deletePost(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		Post p = em.find(Post.class, id);
		if (p != null) {
			em.getTransaction().begin();
			em.remove(p);
			System.out.printf("SUCCESS: Post with id: \"%d\" deleted successfully.", id);
			em.getTransaction().commit();
		} else {

			System.out.printf("ERROR: There's no post with id : \"%d\" in the database.", id);
		}
		em.close();
		emf.close();

	}

	public static void deleteComment(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		Comment c = em.find(Comment.class, id);
		if (c != null) {
			em.getTransaction().begin();
			em.remove(c);
			System.out.printf("SUCCESS: Post with id: \"%d\" deleted successfully.", id);
			em.getTransaction().commit();
		} else {

			System.out.printf("ERROR: There's no post with id : \"%d\" in the database.", id);
		}
		em.close();
		emf.close();

	}
}
