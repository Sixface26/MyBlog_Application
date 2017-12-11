package project.myblog.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import project.myblog.Model.Album;
import project.myblog.R;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumbnail,fav;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.image);
            fav =(ImageView)view.findViewById(R.id.fav);
        }
    }

    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
        sharedpreferences = mContext.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.thumbnail.setImageResource(album.getThumbnail());
        holder.fav.setImageResource(R.drawable.oval_8_copy);
        if (sharedpreferences.contains(Name))
        {
            String favorites = sharedpreferences.getString(Name, "");
            String split[] = favorites.split("\t");
            for(int i=0;i<split.length;i++)
            {
                if(split[i].equals(Integer.toString(position)))
                {
                    holder.fav.setImageResource(R.drawable.oval_8_copy_2);
                }
            }

        }

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                String listString;
                final Bitmap bmap = ((BitmapDrawable)holder.fav.getDrawable()).getBitmap();
                Drawable myDrawable = v.getResources().getDrawable(R.drawable.oval_8_copy);
                final Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
                if (bmap.sameAs(myLogo))
                {
                    listString = sharedpreferences.getString(Name, "");
                    holder.fav.setImageResource(R.drawable.oval_8_copy_2);
                    listString += Integer.toString(position) + "\t";
                    Toast.makeText(mContext,R.string.added,Toast.LENGTH_LONG).show();
                    editor.putString(Name, listString);
                    editor.apply();
                }
                else
                {
                    listString = sharedpreferences.getString(Name, "");
                    holder.fav.setImageResource(R.drawable.oval_8_copy);
                    String newstring="";
                    String split1[] = listString.split("\t");
                    for(int i=0;i<split1.length;i++)
                    {
                        if(split1[i].equals(Integer.toString(position)))
                        {
                            split1[i]="";
                        }
                         newstring += split1[i]+"\t";
                    }
                    Toast.makeText(mContext,R.string.removed,Toast.LENGTH_LONG).show();
                    editor.putString(Name, newstring);
                    editor.apply();

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
