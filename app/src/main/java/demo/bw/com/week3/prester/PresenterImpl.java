package demo.bw.com.week3.prester;

import demo.bw.com.week3.callbak.MyCallBack;
import demo.bw.com.week3.model.ModelImpI;
import demo.bw.com.week3.view.IView;

public class PresenterImpl implements IPresenter{
    private ModelImpI model;
    private IView iView;
    public PresenterImpl(IView iView){
        this.iView=iView;
        model=new ModelImpI();
    }
    @Override
    public void startRequest( final String url,final String mobile, final String password ) {
        model.getData(url, mobile, password, new MyCallBack() {
            @Override
            public void setData( Object user ) {
                iView.success(user);
            }

            @Override
            public void setError( Object error ) {
                iView.error(error);
            }
        });
    }
    //内存泄漏
    public  void onDetatch(){
        if (model!=null){
            model=null;
        }
        if (iView!=null){
            iView=null;
        }
    }
}
