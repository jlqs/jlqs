package com.spl.dell.kaoshi.view.activity;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.spl.dell.kaoshi.R;
import com.spl.dell.kaoshi.model.bean.ResultBean;
import com.spl.dell.kaoshi.model.net.BaseUrl;
import com.spl.dell.kaoshi.persenter.MainPersenter;
import com.spl.dell.kaoshi.tools.ChenJinUtil;
import com.spl.dell.kaoshi.view.constom.BannerImageLoder;
import com.spl.dell.kaoshi.view.interfaces.MainInter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainInter {

    private Banner banner;
    private RecyclerView recyclerView;
    private MainPersenter persenter;
    private List<String> datas = new ArrayList<>();
    private List<String> banners = new ArrayList<>();

    @Override
    void initView() {
        /**
         * 沉浸式    
         */
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            ImmersionBar.with(this).init();
        } else {
            ChenJinUtil.startChenJin(this);
            getSupportActionBar().hide();
        }
        banner = find(R.id.banner);
        recyclerView = find(R.id.recycler);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new BannerImageLoder());
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    @Override
    void initData() {
        persenter = new MainPersenter(this);
        persenter.getData(BaseUrl.BASEURL);
    }

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(ResultBean resultBean) {

        /**
         * 搜索展示  还要进行点击进行搜索任务
         */

        List<ResultBean.RetBean.ListBean> list = resultBean.getRet().getList();
        for (int i = 0; i < list.size(); i++) {
            List<ResultBean.RetBean.ListBean.ChildListBean> childList = resultBean.getRet().getList().get(i).getChildList();
            for (int j = 0; j < childList.size(); j++) {
                banners.add(childList.get(j).getPic().toString());
            }
        }
        banner.setImages(banners);
        banner.start();

//        List<ResultBean.RetBean.HotSearchListBean> hotSearchList = resultBean.getRet().getHotSearchList();
//        for (int i = 0; i < hotSearchList.size(); i++) {
//            datas.add(hotSearchList.get(i).getTagName());
//        }
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
