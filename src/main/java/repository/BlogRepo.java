package repository;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class BlogRepo implements IBlogRepo {
    @Autowired
    EntityManager entityManager;

    @Override
    public Blog save(Blog blog) {
        entityManager.getTransaction().begin();
        entityManager.persist(blog);
        entityManager.getTransaction().commit();
        return blog;
    }

    @Override
    public ArrayList<Blog> fillAll() {
        String hql = "Select b from Blog as b";
        TypedQuery<Blog> query = entityManager.createQuery(hql, Blog.class);
        return (ArrayList<Blog>) query.getResultList();
    }

    @Override
    public void delete(Blog blog) {
        entityManager.getTransaction().begin();
        entityManager.remove(blog);
        entityManager.getTransaction().commit();
    }

    @Override
    public void edit(Blog blog) {
        entityManager.getTransaction().begin();
        entityManager.merge(blog);
        entityManager.getTransaction().commit();

    }
}