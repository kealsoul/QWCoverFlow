# QWCoverFlow
趣玩CoverFlow实现  
![alt](https://github.com/kealsoul/QWCoverFlow/blob/master/icon.png)  
#Look  
![alt](https://github.com/kealsoul/QWCoverFlow/blob/master/GIF.gif)  
__A cool search view coverflow library ,I hope you like it.__  
#usage 
__(1)Add it in your root build.gradle at the end of repositories__
```java
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
__(2)Add the dependency__
```java
dependencies {
	        compile 'com.github.kealsoul:QWCoverFlow:v1.0'
	}
```
__(3)In xml__
```java
        <LinearLayout
            android:id="@+id/lin"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <at.technikum.mti.fancycoverflow.FancyCoverFlow
                android:layout_marginTop="25dp"
                android:layout_marginBottom="100dp"
                android:id="@+id/fancyCoverFlow"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                fcf:actionDistance="auto"
                fcf:maxRotation="0"
                fcf:paddingEnd="10dp"
                fcf:scaleDownGravity="0.1"
                fcf:unselectedAlpha="0.3"
                fcf:unselectedSaturation="0.0"
                fcf:unselectedScale="0.8"           
                />

            <TextView
                android:text="1/10"
                android:textColor="@color/grey3"
                android:gravity="center"
                android:id="@+id/page"
                android:layout_gravity="center_horizontal"
                android:layout_marginTop="-80dp"
                android:layout_width="60dp"
                android:layout_height="60dp"
                android:background="@drawable/mrth_dot"/>
        </LinearLayout>
```
__(4)In java__
```java
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
```
__Thanks:__  
                https://github.com/mcxiaoke/android-volley  
                https://github.com/SVProgressHUD/SVProgressHUD  
                https://github.com/square/picasso  
                https://github.com/davidschreiber/FancyCoverFlow  


