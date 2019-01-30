package survive.sixstones.com.survive.base;

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);
}
