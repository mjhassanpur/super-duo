package barqsoft.footballscores;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

/**
 * @see <a href="https://developer.android.com/guide/topics/appwidgets/index.html"></a>
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FootballScoresWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // update each of the app widgets with the remote adapter
        for (int i = 0, n = appWidgetIds.length; i < n ;i++) {
            int appWidgetId = appWidgetIds[i];
            Intent serviceIntent = new Intent(context, FootballScoresWidgetService.class);
            // Add the app widget ID to the intent extras.
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_main);
            views.setRemoteAdapter(appWidgetId, R.id.widget_list, serviceIntent);
            // Create an Intent to launch MainActivity
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.widget_list, pendingIntent);
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

}
