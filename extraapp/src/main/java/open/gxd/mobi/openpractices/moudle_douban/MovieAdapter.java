package open.gxd.mobi.openpractices.moudle_douban;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.gxd.mobi.openpractices.R;
import open.gxd.mobi.openpractices.Utils.MyLog;
import open.gxd.mobi.openpractices.moudle_douban.data.MovieInfo;

/**
 * Created by edward.ge on 2018/3/13.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemHolder> {


    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_STAGGERED = 1;

    private int type;

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    private boolean isScrolling;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public static final String TAG = "MovieAdapter";
    private List<MovieInfo> infos = new ArrayList<>();

    public MovieAdapter(List<MovieInfo> list, int type) {
        this.infos = list;
        this.type = type;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyLog.d("MovieAdapter", "onCreateViewHolder");
        View view;
        if (type == TYPE_NORMAL) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_douban_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_douban_staggered_item, parent, false);
        }
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
//        MyLog.d("MovieAdapter", "onBindViewHolder");
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
        MovieInfo info = infos.get(position);
        holder.tvRate.setText(String.valueOf(info.getRateInfo().getAverage()));
        holder.tvTitle.setText(info.getName());
        String s = info.getYear() + "/" + formGendr(info.getGender()) + "/" + info.getDirectors().get(0).getName() + "/" + info.getCasts().get(0).getName();
        holder.tvInfo.setText(s);
        if (!isScrolling) {
//            MyLog.d("MovieAdapter", "onBindViewHolder" + "not scrolling");
            Glide.with(holder.itemView.getContext())
                    .load(info.getImages()
                            .getSmall()).into(holder.tvImage);
        }

    }

    private String formGendr(List<String> gender) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gender.size(); i++) {
            if (i == gender.size() - 1) {
                sb.append(gender.get(i));
            } else {
                sb.append(gender.get(i)).append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    public int getItemCount() {
        if (infos != null) {
            return infos.size();
        }

        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    void setInfos(List<MovieInfo> list) {
        this.infos = list;
        notifyDataSetChanged();
    }


    public static class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_image)
        ImageView tvImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_rate)
        TextView tvRate;
        @BindView(R.id.tv_info)
        TextView tvInfo;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
