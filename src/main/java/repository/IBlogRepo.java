package repository;

import model.Blog;

import java.sql.Blob;
import java.util.ArrayList;

public interface IBlogRepo {
    Blog save(Blog blog);

    ArrayList<Blog> fillAll();
    void delete(Blog blog);
    void edit(Blog blog);
}
