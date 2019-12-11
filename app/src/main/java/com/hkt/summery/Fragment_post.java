package com.hkt.summery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_post extends Fragment {

    private RecyclerView mRecylerView;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Category> categoryArrayListTemp;
    private RequestQueue requestQueue;
    private GridLayoutManager layoutManager;

    public CategoryAdapter getCategoryAdapter() {
        return categoryAdapter;
    }

    public ArrayList<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public Fragment_post(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        mRecylerView = view.findViewById(R.id.recycler_view);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setItemViewCacheSize(20);
        layoutManager = new GridLayoutManager(getActivity(),3); // seperate into 3 columm
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecylerView.setLayoutManager(layoutManager);
        requestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();
        categoryArrayList = new ArrayList<>();

        return view;
    }

    private void parseJSON() {
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("meals");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject json = jsonArray.getJSONObject(i);

                                String name = json.getString("strMeal");
                                String imageURL = json.getString("strMealThumb");
                                Log.i("Test", name + "  " + imageURL);
                                categoryArrayList.add(new Category(name,imageURL));

                            }

                            categoryAdapter = new CategoryAdapter(getActivity(), categoryArrayList);
                            mRecylerView.setAdapter(categoryAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
