package barqsoft.footballscores;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies
{
    public static final int SERIE_A = 357;
    public static final int PREMIER_LEGAUE = 354;
    public static final int CHAMPIONS_LEAGUE = 362;
    public static final int PRIMERA_DIVISION = 358;
    public static final int BUNDESLIGA = 351;

    public static final int DATE_COL = 1;
    public static final int TIME_COL = 2;
    public static final int HOME_COL = 3;
    public static final int AWAY_COL = 4;
    public static final int LEAGUE_COL = 5;
    public static final int HOME_GOALS_COL = 6;
    public static final int AWAY_GOALS_COL = 7;
    public static final int MATCH_ID = 8;
    public static final int MATCH_DAY = 9;

    // empty string
    public static final String EMPTY_STRING = "";

    public static String getLeague(int league_num, Context context)
    {
        Resources res = context.getResources();
        switch (league_num)
        {
            case SERIE_A : return res.getString(R.string.seriaa);
            case PREMIER_LEGAUE : return res.getString(R.string.premierleague);
            case CHAMPIONS_LEAGUE : return res.getString(R.string.champions_league);
            case PRIMERA_DIVISION : return res.getString(R.string.primeradivison);
            case BUNDESLIGA : return res.getString(R.string.bundesliga);
            default: return res.getString(R.string.not_known_league);
        }
    }
    public static String getMatchDay(int match_day,int league_num, Context context)
    {
        Resources res = context.getResources();
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return res.getString(R.string.group_stages_6);
            }
            else if(match_day == 7 || match_day == 8)
            {
                return res.getString(R.string.first_knockout_round);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return res.getString(R.string.quarter_final);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return res.getString(R.string.semi_final);
            }
            else
            {
                return res.getString(R.string.final_text);
            }
        }
        else
        {
            return res.getString(R.string.matchday_text) + String.valueOf(match_day);
        }
    }

    public static String getScores(int home_goals,int awaygoals, Context context)
    {
        Resources res = context.getResources();
        if(home_goals < 0 || awaygoals < 0)
        {
            return res.getString(R.string.dash);
        }
        else
        {
            return String.valueOf(home_goals) + res.getString(R.string.dash) + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname, Context context)
    {
        Resources res = context.getResources();
        if (teamname == null)
            return R.drawable.no_icon;
        else if (teamname.equals(res.getString(R.string.arsenal)))
            return R.drawable.arsenal;
        else if (teamname.equals(res.getString(R.string.manchester)))
            return R.drawable.manchester_united;
        else if (teamname.equals(res.getString(R.string.swansea)))
            return R.drawable.swansea_city_afc;
        else if (teamname.equals(res.getString(R.string.leicester)))
            return R.drawable.leicester_city_fc_hd_logo;
        else if (teamname.equals(res.getString(R.string.everton)))
            return R.drawable.everton_fc_logo1;
        else if (teamname.equals(res.getString(R.string.west_ham)))
            return R.drawable.west_ham;
        else if (teamname.equals(res.getString(R.string.tottenham)))
            return R.drawable.tottenham_hotspur;
        else if (teamname.equals(res.getString(R.string.west_bromwich_albion)))
            return R.drawable.west_bromwich_albion_hd_logo;
        else if (teamname.equals(res.getString(R.string.sunderland)))
            return R.drawable.sunderland;
        else if (teamname.equals(res.getString(R.string.stoke_city)))
            return R.drawable.stoke_city;
        else
            return R.drawable.no_icon;
    }
}
