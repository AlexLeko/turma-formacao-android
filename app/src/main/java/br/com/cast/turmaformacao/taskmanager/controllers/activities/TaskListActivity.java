package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.TaskListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;


public class TaskListActivity extends AppCompatActivity {

    private ListView listViewTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        bindListViewTaskList();
    }

    @Override
    protected void onResume() {
        TaskListAdapter adapter = (TaskListAdapter) listViewTaskList.getAdapter();
        List<Task> values = TaskBusinessService.getInstance().findAll();
        adapter.setDataValues(values);
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    private void bindListViewTaskList() {
        listViewTaskList = (ListView) findViewById(R.id.listViewTaskList);
        List<Task> values = TaskBusinessService.getInstance().findAll();
        listViewTaskList.setAdapter(new TaskListAdapter(this, values));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                onMenuAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent goToTaskFormActivity = new Intent(TaskListActivity.this, TaskFormActivity.class);
        startActivity(goToTaskFormActivity);
    }
}
