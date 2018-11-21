

package euphoria.psycho.media;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;

public class AutoFitRecyclerView extends RecyclerView {

    private GridLayoutManager mGridLayoutManager;
    private int mColumnWidth = -1;
    private int mSpanCount = -1;

    public AutoFitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            mColumnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

        mGridLayoutManager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(mGridLayoutManager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (mSpanCount == -1 && mColumnWidth > 0) {
            int ratio = getMeasuredWidth() / mColumnWidth;
            int spanCount = Math.max(1, ratio);
            mGridLayoutManager.setSpanCount(spanCount);
        } else
            mGridLayoutManager.setSpanCount(mSpanCount);

    }

    public void setColumnWidth(int width) {
        mColumnWidth = width;
    }

    public int getPerfectColumnWidth(int columnWidth, int margin) {

        WindowManager wm = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int displayWidth = display.getWidth() - margin;

        int remainingSpace = displayWidth % columnWidth;
        int ratio = displayWidth / columnWidth;
        int spanCount = Math.max(1, ratio);

        return (columnWidth + (remainingSpace / spanCount));
    }

    public int getColumnWidth() {
        return mColumnWidth;
    }

    public void setNumColumns(int spanCount) {
        mSpanCount = spanCount;
    }

    public void setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        mGridLayoutManager.setSpanSizeLookup(spanSizeLookup);
    }

}
