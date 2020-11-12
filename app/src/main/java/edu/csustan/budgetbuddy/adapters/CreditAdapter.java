package edu.csustan.budgetbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.csustan.budgetbuddy.Credit;
import edu.csustan.budgetbuddy.R;

public class CreditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;

    private final List<Object> listRecyclerItem;

    public CreditAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView bankName;
        private TextView bankDescription;
        private TextView bestFor;
        private TextView applicationLink;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            bankName = (TextView) itemView.findViewById(R.id.bankName);
            bankDescription = (TextView) itemView.findViewById(R.id.bankDescription);
            bestFor = (TextView) itemView.findViewById(R.id.bestFor);
            applicationLink = (TextView) itemView.findViewById(R.id.applicationLink);

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.item_credit, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);
        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder)viewHolder;
                Credit credit = (edu.csustan.budgetbuddy.Credit) listRecyclerItem.get(i);

                itemViewHolder.bankName.setText(credit.getBankName());
                itemViewHolder.bankDescription.setText(credit.getBankDescription());
                itemViewHolder.bestFor.setText(credit.getBestFor());
                itemViewHolder.applicationLink.setText(credit.getApplicationLink());

        }
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
