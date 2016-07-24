package com.keal.qwcoverflow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

public class MainActivity extends Activity {
    private ImageView back;
    private TextView page;
    private FancyCoverFlow fancyCoverFlow;
    private ImageView image_bg;
    private int wlgq_posion;

    private ViewGroupExampleAdapter viewGroupExampleAdapter;

    private static int width;
    private List<WLGQ> list;
    private Request<JSONObject> requestSpeical;
    private RequestQueue requestQueue;

    //页数
    private int pagesize = 0;

    private SVProgressHUD mSVProgressHUD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels-250;

        fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.fancyCoverFlow);
        viewGroupExampleAdapter = new ViewGroupExampleAdapter();
        fancyCoverFlow.setAdapter(viewGroupExampleAdapter);

        httpSearchInfo(pagesize + "");

        fancyCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UtilTools.toast(MainActivity.this,"你点击了商品");

            }
        });

        fancyCoverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UtilTools.log("监听器               " + position);
                int i = position+1;

                page.setText(i + "/" + list.size());
                if (i == list.size()) {
                    pagesize++;
                    httpSearchInfo(pagesize + "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private class ViewGroupExampleAdapter extends FancyCoverFlowAdapter {
        private CustomViewGroup customViewGroup;
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Integer getItem(int i) {
            return 0;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getCoverFlowItem(final int position, View reuseableView, ViewGroup viewGroup) {
            if (reuseableView != null) {
                customViewGroup = (CustomViewGroup) reuseableView;
            } else {
                customViewGroup = new CustomViewGroup(viewGroup.getContext());
                customViewGroup.setLayoutParams(new FancyCoverFlow.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
                customViewGroup.setBackgroundResource(R.drawable.wrth_bg);
            }
            Picasso.with(MainActivity.this).load(list.get(position).getOriginal_img()).placeholder(R.mipmap.morentu)
                    .error(R.mipmap.morentu).into(customViewGroup.getImageView());
            customViewGroup.getTextView().setText(list.get(position).getGoods_name());
            customViewGroup.getPrice().setText("￥"+list.get(position).getPromote_price());
            customViewGroup.getGoods_context().setText("         "+list.get(position).getWenan());
            if(!list.get(position).getPromote_price().equals(list.get(position).getShop_price())){
                customViewGroup.getNoprice().setText("原价：￥" + list.get(position).getShop_price());
                customViewGroup.getNoprice().getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }else {
                customViewGroup.getNoprice().setVisibility(View.GONE);
            }

            customViewGroup.getLinearLayout2_1().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilTools.toast(MainActivity.this,"你点击了喜欢");
                }
            });

            customViewGroup.getLinearLayout2_2().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilTools.toast(MainActivity.this,"你点击了分享");
                }
            });

            return customViewGroup;
        }
    }

    private static class CustomViewGroup extends LinearLayout {

        // =============================================================================
        // Child views
        // =============================================================================

        private TextView textView,price,noprice,goods_context;

        private ImageView imageView,goods_context_image;

        private LinearLayout linearLayout,linearLayout2,linearLayout2_1,linearLayout2_2,linearLayout2_3;

        private RelativeLayout relativeLayout;

        private TextView textView1,textView2,textView3;

        private ImageView imageView1,imageView2;


        // =============================================================================
        // Constructor
        // =============================================================================

        private CustomViewGroup(Context context) {
            super(context);

            this.setOrientation(VERTICAL);

            this.textView = new TextView(context);
            this.price = new TextView(context);
            this.noprice = new TextView(context);
            this.imageView = new ImageView(context);
            this.linearLayout = new LinearLayout(context);
            this.linearLayout2 = new LinearLayout(context);
            this.goods_context = new TextView(context);
            this.goods_context_image = new ImageView(context);
            this.relativeLayout = new RelativeLayout(context);
            this.linearLayout2_1 = new LinearLayout(context);
            this.linearLayout2_2 = new LinearLayout(context);
            this.linearLayout2_3 = new LinearLayout(context);
            this.textView1 = new TextView(context);
            this.textView2 = new TextView(context);
            this.textView3 = new TextView(context);
            this.imageView1 = new ImageView(context);
            this.imageView2 = new ImageView(context);


            LayoutParams ImagelayoutParams = new LayoutParams(width, width);
            ImagelayoutParams.topMargin = 0;
            ImagelayoutParams.bottomMargin = 0;
            ImagelayoutParams.leftMargin = 0;
            ImagelayoutParams.rightMargin = 0;
            ImagelayoutParams.gravity= Gravity.CENTER_HORIZONTAL;
            this.imageView.setLayoutParams(ImagelayoutParams);
            this.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.imageView.setAdjustViewBounds(true);
            this.addView(this.imageView);

            LayoutParams NamelayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            NamelayoutParams.topMargin = dip2px(context,10);
            NamelayoutParams.leftMargin = dip2px(context,15);
            NamelayoutParams.rightMargin = dip2px(context,15);
            this.textView.setLayoutParams(NamelayoutParams);
            this.textView.setTextColor(Color.parseColor("#4E4E4E"));
            this.textView.setSingleLine(true);
            this.textView.setTextSize(16);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.addView(this.textView);

            LayoutParams linearLayoutlayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayoutlayoutParams.topMargin = dip2px(context,10);
            linearLayoutlayoutParams.leftMargin = dip2px(context,15);
            linearLayoutlayoutParams.rightMargin = dip2px(context,15);
            this.linearLayout.setLayoutParams(linearLayoutlayoutParams);
            this.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            this.addView(this.linearLayout);

            LayoutParams pricelayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.price.setLayoutParams(pricelayoutParams);
            this.price.setTextColor(Color.parseColor("#D61B22"));
            this.linearLayout.addView(this.price);

            LayoutParams nopricelayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            nopricelayoutParams.leftMargin = dip2px(context,15);
            this.noprice.setLayoutParams(nopricelayoutParams);
            this.noprice.setTextColor(Color.parseColor("#C2C2C2"));
            this.noprice.setTextSize(12);
            this.linearLayout.addView(this.noprice);


            LayoutParams RelativeLayoutlayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayoutlayoutParams.topMargin = dip2px(context,10);
            RelativeLayoutlayoutParams.leftMargin = dip2px(context,15);
            RelativeLayoutlayoutParams.rightMargin = dip2px(context,15);
            this.relativeLayout.setLayoutParams(RelativeLayoutlayoutParams);
            this.relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            this.addView(this.relativeLayout);

            LayoutParams numlayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.goods_context_image.setLayoutParams(numlayoutParams);
            this.goods_context_image.setBackgroundResource(R.mipmap.dun);
            this.relativeLayout.addView(this.goods_context_image);

            LayoutParams num_textlayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.goods_context.setLayoutParams(num_textlayoutParams);
            this.goods_context.setTextColor(Color.parseColor("#888888"));
            this.goods_context.setEllipsize(TextUtils.TruncateAt.END);
            this.goods_context.setLines(2);
            this.relativeLayout.addView(this.goods_context);

            LayoutParams linearLayoutlayoutParams2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayoutlayoutParams2.topMargin = dip2px(context,10);
            linearLayoutlayoutParams2.leftMargin = dip2px(context,15);
            linearLayoutlayoutParams2.rightMargin = dip2px(context,15);
            linearLayoutlayoutParams2.gravity = Gravity.CENTER;
            this.linearLayout2.setLayoutParams(RelativeLayoutlayoutParams);
            this.addView(this.linearLayout2);

            LayoutParams linearLayoutlayoutParams2_0 = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayoutlayoutParams2_0.weight = 1;
            linearLayoutlayoutParams2_0.bottomMargin = dip2px(context,10);
            this.linearLayout2_1.setLayoutParams(linearLayoutlayoutParams2_0);
            this.linearLayout2_1.setGravity(Gravity.CENTER);
            this.linearLayout2_1.setOrientation(HORIZONTAL);

            this.linearLayout2_2.setLayoutParams(linearLayoutlayoutParams2_0);
            this.linearLayout2_2.setGravity(Gravity.CENTER);
            this.linearLayout2_2.setOrientation(HORIZONTAL);

            this.linearLayout2_3.setLayoutParams(linearLayoutlayoutParams2_0);
            this.linearLayout2_3.setGravity(Gravity.CENTER);
            this.linearLayout2_3.setOrientation(HORIZONTAL);
            this.linearLayout2.addView(this.linearLayout2_1);
            this.linearLayout2.addView(this.linearLayout2_2);
            this.linearLayout2.addView(this.linearLayout2_3);

            LayoutParams imageviewlayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.textView1.setLayoutParams(imageviewlayoutParams);
            this.textView1.setText("喜欢");
            this.textView1.setTextColor(Color.parseColor("#888888"));
            LayoutParams imageview2layoutParams = new LayoutParams(dip2px(context,20), dip2px(context,18));
            imageview2layoutParams.rightMargin = dip2px(context,8);
            this.imageView1.setLayoutParams(imageview2layoutParams);
            this.imageView1.setBackgroundResource(R.mipmap.index_like);

            this.textView2.setLayoutParams(imageviewlayoutParams);
            this.textView2.setText("分享");
            this.textView2.setTextColor(Color.parseColor("#888888"));
            this.imageView2.setLayoutParams(imageviewlayoutParams);
            this.imageView2.setBackgroundResource(R.mipmap.fenxiang);

            this.textView3.setLayoutParams(imageviewlayoutParams);
            this.textView3.setText("查看详情");
            this.textView3.setTextColor(Color.parseColor("#888888"));

            this.linearLayout2_1.addView(imageView1);
            this.linearLayout2_1.addView(textView1);
            this.linearLayout2_2.addView(imageView2);
            this.linearLayout2_2.addView(textView2);
            this.linearLayout2_3.addView(textView3);
        }

        // =============================================================================
        // Getters
        // =============================================================================

        private TextView getTextView() {
            return textView;
        }

        private ImageView getImageView() {
            return imageView;
        }

        private TextView getPrice(){
            return  price;
        }

        public TextView getNoprice() {
            return noprice;
        }

        public TextView getGoods_context() {
            return goods_context;
        }

        public ImageView getImageView1(){
            return  imageView1;
        }

        public LinearLayout getLinearLayout2_1(){
            return linearLayout2_1;
        }

        public  LinearLayout getLinearLayout2_2(){
            return linearLayout2_2;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private void init(){
        mSVProgressHUD = new SVProgressHUD(this);
        mSVProgressHUD.showWithStatus("加载中...");

        page = (TextView)findViewById(R.id.page);
        image_bg = (ImageView)findViewById(R.id.image_bg);

        requestQueue = Volley.newRequestQueue(this);
        list = new ArrayList<>();
    }

    private void httpSearchInfo(final String page) {
        String url = "http://qwapi.quwan.com/zhuanti/zhuanti/wolegequ";
        Map<String, String> map = new HashMap<>();
        map.put("pagesize", "10");
        map.put("page", page);
        map.put("from", "Android");

        requestSpeical = new NormalPostRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (page.equals("0")) {
                            list.clear();
                        }
                        mSVProgressHUD.dismiss();
                        UtilTools.log("MainActivity" + "搜索" + response.toString());
                        try {

                            if(response.getString("message").equals("暂无相关数据")){
                                UtilTools.toast(MainActivity.this,response.getString("message"));
                            }

                            JSONObject datajson = response.getJSONObject("data");
                            JSONArray goodsjson = datajson.getJSONArray("goods_list");

                            for (int i = 0; i < goodsjson.length(); i++) {
                                JSONObject item = goodsjson.getJSONObject(i);
                                WLGQ specialInfo = new WLGQ();
                                specialInfo.setGoods_id(item.getString("goods_id"));
                                specialInfo.setPromote_price(item.getString("promote_price"));
                                specialInfo.setShop_price(item.getString("shop_price"));
                                specialInfo.setGoods_name(item.getString("goods_name"));
                                specialInfo.setOriginal_img(item.getString("original_img"));
                                specialInfo.setWenan(item.getString("wenan"));
                                specialInfo.setIs_like(item.getString("is_like"));
                                specialInfo.setShare_link(item.getString("share_link"));
                                list.add(specialInfo);
                            }
                            UtilTools.log(list.size() + "");
                            viewGroupExampleAdapter.notifyDataSetChanged();
                            if(pagesize==0){
                                fancyCoverFlow.setSelection(wlgq_posion);
                            }

                        } catch (Exception e) {
                            UtilTools.log("SpecialActivity " + "json" + e.getMessage());
                            mSVProgressHUD.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*Log.e(TAG, error.getMessage(), error);*/
                mSVProgressHUD.dismiss();
                UtilTools.log("SpecialActivity " + "搜索" + error.getMessage());
                UtilTools.toast(MainActivity.this, "网络状态不好");
            }
        }, map);
        requestQueue.add(requestSpeical);
        requestQueue.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



}
