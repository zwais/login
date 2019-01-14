package demo.bw.com.week3.view;

public interface IView<T> {
    void  success(T data);
    void  error(T error);
}
