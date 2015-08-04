package barqsoft.footballscores;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * @see <a href="https://developer.android.com/guide/topics/appwidgets/index.html"></a>
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FootballScoresWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FootballScoresRemoteViewsFactory(getApplicationContext());
    }

    public class FootballScoresRemoteViewsFactory implements RemoteViewsFactory {
        private Context mContext;
        private Cursor mCursor;

        public FootballScoresRemoteViewsFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() { }

        @Override
        public void onDataSetChanged() {
            if (mCursor != null) {
                mCursor.close();
            }
            mCursor = mContext.getContentResolver().query(DatabaseContract.BASE_CONTENT_URI, null, null, null, null);
        }

        @Override
        public void onDestroy() { }

        @Override
        public int getCount() {
            if (mCursor != null) {
                return mCursor.getCount();
            }
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
            String matchTime = Utilies.EMPTY_STRING;
            String homeTeam = Utilies.EMPTY_STRING;
            String awayTeam = Utilies.EMPTY_STRING;
            String finalScore = Utilies.EMPTY_STRING;
            if (mCursor.moveToPosition(position)) {
                matchTime = mCursor.getString(Utilies.TIME_COL);
                homeTeam = mCursor.getString(Utilies.HOME_COL);
                awayTeam = mCursor.getString(Utilies.AWAY_COL);
                finalScore = Utilies.getScores(mCursor.getInt(Utilies.HOME_GOALS_COL), mCursor.getInt(Utilies.AWAY_GOALS_COL));
            }
            views.setTextViewText(R.id.widget_time, matchTime);
            views.setTextViewText(R.id.widget_home_team, homeTeam);
            views.setTextViewText(R.id.widget_away_team, awayTeam);
            views.setTextViewText(R.id.widget_final_score, finalScore);
            views.setOnClickFillInIntent(R.id.widget_item_container, new Intent());
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            if (mCursor != null) {
                return mCursor.getLong(mCursor.getColumnIndex(DatabaseContract.scores_table.MATCH_ID));
            }
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
