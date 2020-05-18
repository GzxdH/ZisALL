package com.zxd.zisall.base;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V, P extends BasePresent<V>> extends Fragment implements BaseView {

//    protected P presenter;
//    protected ImmersionBar mImmersionBar;
//    protected View mView;  //缓存Fragment view
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (mView == null) {
//            Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.ThemeLight);
//            LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
//            mView = localInflater.inflate(getContentViewLayoutID(), null);
//        }
//        ViewGroup parent = (ViewGroup) mView.getParent();
//        if (parent != null) {
//            parent.removeView(mView);
//        }
//        return mView;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
//        //创建presenter
//        presenter = initPresenter();
//        initViews(savedInstanceState);
//
//        initEvent();
////        初始化沉浸式
//        if (isImmersionBarEnabled()) {
//            initImmersionBar();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        // 在Activity中初始化P，并且连接V
//        presenter.attach((V) this);
//        initData();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //在onDestroy()生命周期中释放P中引用的V。
//        presenter.detach();
//        //在onDestroy()生命周期中取消所有子线程里面的网络连接。
//        OkGo.getInstance().cancelTag(getActivity());
//        if (mImmersionBar != null) {
//            mImmersionBar.destroy();
//        }
//    }
//
//    public abstract P initPresenter();
//
//    protected abstract void initViews(Bundle savedInstanceState);
//
//    protected abstract void initData();
//
//    public abstract void initEvent();
//
//    /**
//     * 是否可以使用沉浸式
//     *
//     * @return the boolean
//     */
//    protected boolean isImmersionBarEnabled() {
//        return true;
//    }
//
//    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.keyboardEnable(true).statusBarDarkFont(false, 0.2f).init();
//    }
//
//    protected abstract int getContentViewLayoutID();
//
//    protected <T extends View> T getId(int id) {
//        return (T) mView.findViewById(id);
//    }
//
//    @Override
//    public void showProgress() {
//        ShowDialog.showDialog(getActivity(), "", true, null);
//    }
//
//    @Override
//    public void hideProgress() {
//        ShowDialog.dissmiss();
//    }
//
//    @Override
//    public void toast(CharSequence s) {
//        ToastUtils.showShortToast(s);
//    }
//
//    @Override
//    public void showNullLayout() {
//
//    }
//
//    @Override
//    public void hideNullLayout() {
//
//    }
//
//    @Override
//    public void showErrorLayout() {
//      /*  getId(R.id.error_text).setVisibility(View.VISIBLE);
//        getId(R.id.error_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initData();
//            }
//        });*/
//    }
//
//    @Override
//    public void hideErrorLayout() {
////        getId(R.id.error_text).setVisibility(View.GONE);
//    }
}
