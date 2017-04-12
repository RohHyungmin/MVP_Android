# MVP Android
안드로이드에서 MVP 패턴을 구현해 봅니다.

## MVP Interface
아래와 같이 View 와 Presenter 에서 사용할 인터페이스를 미리 정의하고 사용한다.
* 주의해서 볼 부분은 View.setPresenter 와 Presenter.attachView 이다. 이를 통해 상호참조 형태인 MVP 구조가 된다.
```java
public interface MainContract {
    interface View {
        void setMessage(String msg);
        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void loadItems();
        void loadActivity(Context context);
    }
}
```

## MVP with Fragment
액티비티에서 프래그먼트를 뷰로 사용하는 형태.
* 3번에서 뷰를 생성한 후 프레젠터에 attach 해주고 프레젠터의 attach 메서드 에서는 넘겨받은 뷰에 자신을 넘겨줌으로서 상호 참조형태가 된다.
### Activity
```java
public class MainActivity extends AppCompatActivity{

    MainContract.Presenter presenter;
    MainFragment view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 프레젠터 생성
        presenter = new MainPresenter();
        // 2. 뷰 생성 - 프래그먼트를 뷰로 사용하는 형태
        view = new MainFragment();
        // 3. 생성한 뷰를 프레젠터에 넘겨준다.
        presenter.attachView(view);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.framelayout, view).commit();
    }
}
```
### Presenter
* 1 번에서 넘겨받은 뷰의 인터페이스를 호출하면서 자신을 넘겨주기 때문에 상호 참조형태로 상대방의 약속된 인터페이스를 사용할 수 있게 된다.
```java
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
        // 1. 받은 뷰의 setPresenter 인터페이스를 통해 프레젠터 자신을 넘겨준다.
        this.view.setPresenter(this);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadItems() {
        view.setMessage("Main Activity!!!");
    }

    @Override
    public void loadActivity(Context context) {
        Intent intent = new Intent(context, TaskActivity.class);
        context.startActivity(intent);
    }
}
```

## MVP with Activity
액티비티를 뷰로 사용하는 형태의 MVP 프래그먼트를 사용하는 예제와는 다르게 xml 에 값을 세팅하는 뷰 메서드들이 액티비티에 존재하게 된다
```java
public class TaskActivity extends AppCompatActivity implements TaskContract.View, View.OnClickListener{

    private TaskContract.Presenter presenter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        tv = (TextView) findViewById(R.id.textView);

        // 1. 프레젠터를 생성한다.
        presenter = new TaskPresenter();
        // 2. 프레젠터에 뷰(액티비티 자신)를 넘겨준다
        presenter.attachView(this);
        presenter.loadItems();
    }

    @Override
    public void setMessage(String msg) {
        tv.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onClick(View view) {

    }
}
```