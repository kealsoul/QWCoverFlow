# QWCoverFlow
趣玩CoverFlow实现  
![alt](https://github.com/kealsoul/QWCoverFlow/blob/master/icon.png)  
#Look  
![alt](https://github.com/kealsoul/QWCoverFlow/blob/master/GIF.gif)  
__A cool search view coverflow library ,I hope you like it.__  
#usage 
#(1)In xml
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

