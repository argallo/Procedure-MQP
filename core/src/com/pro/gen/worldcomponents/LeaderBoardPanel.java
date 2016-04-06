package com.pro.gen.worldcomponents;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.DatabaseManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gallo on 2/9/2016.
 */
public class LeaderBoardPanel extends Group {

    private ScrollPane scrollPane;
    private Group rows;

    public LeaderBoardPanel(){

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "Jim");
        DatabaseManager.getInstance().makeDBCall(DatabaseManager.LEADERBOARD, params, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String data = httpResponse.getResultAsString();
                LogUtils.Log("RESPONSE AS STRING" + data);
                ArrayList<String> usernames = XmlManager.getInstance().getUsernames(data);
                ArrayList<String> scores = XmlManager.getInstance().getScores(data);
                for(String user:usernames){
                    LogUtils.Log("USER"+user);
                }


                setSize(550, 500);
                rows = new Group();
                rows.setSize(550, usernames.size() * 58);
                loadRows(usernames.size());
                scrollPane = new ScrollPane(rows);
                if(rows.getHeight() < 500){
                    //rows.setY(500-rows.getHeight());
                    setY(500-rows.getHeight()+15);
                }
                scrollPane.setFillParent(true);
                addActor(scrollPane);
                appendNames(usernames, scores);
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });

    }


    public void loadRows(int size){
        for(int i = 0; i < size; i++){
            TintedImage row = new TintedImage(Pic.Pixel, Tint.LEADER_RED);
            row.setSize(550, 56);
            row.setPosition(0, i*58);
            rows.addActor(row);
        }
    }

    public void appendNames(ArrayList<String> usernames, ArrayList<String> scores){
        String names = "";
        String crystalCount = "";
        for(int i = 0; i < usernames.size()-1; i++){
            names = (names+(i+1)+". "+usernames.get(i)+"\n");
            crystalCount = (crystalCount+(scores.get(i))+"\n");
        }
        names = (names+(usernames.size())+". "+usernames.get(usernames.size()-1));
        crystalCount = (crystalCount+(scores.get(scores.size()-1)));

        TextLabel textLabel = new TextLabel(names, Assets.getInstance().getMidFont(), Align.left);
        textLabel.setPosition(10, 0);

        TextLabel crystalLabel = new TextLabel(crystalCount, Assets.getInstance().getMidFont(), Align.right);
        crystalLabel.setPosition(405, 0);

        rows.addActor(textLabel);
        rows.addActor(crystalLabel);

        for(int i = 0; i < usernames.size(); i++){
            TintedImage crystal = new TintedImage(Pic.Mega_Crystal);
            crystal.setSize(60,50);
            crystal.setPosition(490, i*58);
            rows.addActor(crystal);
        }
    }



}
