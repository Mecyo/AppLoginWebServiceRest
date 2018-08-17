package br.edu.ifba.samuv.util;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.samuv.R;
import br.edu.ifba.samuv.activities.MainActivity;
import br.edu.ifba.samuv.connection.RetrofitConfig;
import br.edu.ifba.samuv.models.Profissional;
import br.edu.ifba.samuv.models.Tecnica;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomDialogFragment extends DialogFragment {
    private EditText mEditText;
    private Button btnConfirm;
    private List<Tecnica> tecnicas =  new ArrayList<>();

    public CustomDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static CustomDialogFragment newInstance(String title) {
        CustomDialogFragment frag = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_tecnicas, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = view.findViewById(R.id.txt_your_name);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Call<List<Tecnica>> callProf = new RetrofitConfig().samuvService().getTecnicasList();

        final View main = view;
        callProf.enqueue(new Callback<List<Tecnica>>() {
            @Override
            public void onResponse(Call<List<Tecnica>> call, Response<List<Tecnica>> response) {
                // pegar a resposta
                if (response.isSuccessful()) {
                    tecnicas = response.body();
                }
                else {
                    Toast.makeText(main.getContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tecnica>> call, Throwable t) {
                // tratar algum erro
                Toast.makeText(main.getContext(), "Não foi possível carregar a lista de técnicas: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).iniciarAtendimento(tecnicas);
            }
        });
    }

}
