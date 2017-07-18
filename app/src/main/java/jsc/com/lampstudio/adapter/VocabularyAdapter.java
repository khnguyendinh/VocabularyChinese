package jsc.com.lampstudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import jsc.com.lampstudio.Models.VocabularyObj;
import jsc.com.lampstudio.vocabularychinese.MainActivity;
import jsc.com.lampstudio.vocabularychinese.R;

/**
 * Created by Administrator PC on 11/23/2015.
 */
public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.HolderX> {

    private Context context;
    private ArrayList<VocabularyObj> listVcabulary;
    LayoutInflater inflater;
    public VocabularyAdapter(ArrayList<VocabularyObj> grammarObjs, Context context) {
        this.listVcabulary = grammarObjs;
        this.context = context;
    }

    @Override
    public HolderX onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.itemvocabulary, parent, false);
        HolderX holder = new HolderX(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderX holder, int position) {
        holder.nameLesson.setText(listVcabulary.get(position).getEnglish());
        holder.idLesson.setText(context.getString(R.string.lesson)+" "+(position+1));
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return listVcabulary.size();
    }

    class HolderX extends RecyclerView.ViewHolder {
        TextView nameLesson;
        TextView idLesson;
        int pos;
        public HolderX(View itemView) {
            super(itemView);
            nameLesson = (TextView) itemView.findViewById(R.id.txt_story_title);
            idLesson = (TextView) itemView.findViewById(R.id.txt_lesson);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, MainActivity.class);
//                    intent.putExtra(Constant.KEY_ID, listVcabulary.get(pos).getG_id());
//                    context.startActivity(intent);
                }
            });
        }
    }

}

