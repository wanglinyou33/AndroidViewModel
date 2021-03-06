package eu.inloop.viewmodel.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.SampleApplication;
import eu.inloop.viewmodel.sample.viewmodel.PageModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IPageView;

public class PagerFragment extends ViewModelBaseFragment<IPageView, PageModel> implements IPageView {

    public static PagerFragment newInstance(int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        final PagerFragment fragment = new PagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.text)).setText(Integer.toString(getArguments().getInt("position")));
        setModelView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // watch for memory leaks
        RefWatcher refWatcher = SampleApplication.getRefWatcher(requireActivity());
        refWatcher.watch(this);
    }
}
