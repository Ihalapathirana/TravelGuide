package project.guide.anu.travellanka.adapterpackage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import project.guide.anu.travellanka.R;

/**
 * Created by Anu on 8/25/2015.
 */
public class CustomSwipeAdapter extends PagerAdapter {

    private int[] image_resorce= {R.drawable.image3,R.drawable.image2,R.drawable.image4,R.drawable.image5,R.drawable.image6};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx){
        this.ctx=ctx;
    }
    @Override
    public int getCount() {
        return image_resorce.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view==(LinearLayout)o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View item_view=layoutInflater.inflate(R.layout.swipe_viwer,container,false);
        ImageView imageView= (ImageView) item_view.findViewById(R.id.imageView);

        imageView.setImageResource(image_resorce[position]);
        container.addView(item_view);
        return item_view;
    }

    //destroy image from heap
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
