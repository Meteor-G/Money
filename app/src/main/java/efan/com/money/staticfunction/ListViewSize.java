package efan.com.money.staticfunction;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewSize {
	public static void SetListViewSize(ListView listview) {
		ListAdapter listAdapter = listview.getAdapter();

		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listview);
			listItem.measure(MeasureSpec.UNSPECIFIED,
					MeasureSpec.UNSPECIFIED);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listview.getLayoutParams();
		params.height = totalHeight
				+ (listview.getDividerHeight() * (listAdapter.getCount() - 1) + 320);
		listview.setLayoutParams(params);
	}

}
