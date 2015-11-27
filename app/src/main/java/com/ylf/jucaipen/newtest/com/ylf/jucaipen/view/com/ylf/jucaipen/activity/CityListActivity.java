package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.ylf.jucaipen.newtest.R;
import com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.adapter.SuperTreeViewAdapter;
import com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.adapter.ThreeViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class CityListActivity extends Activity {
    private ExpandableListView expandableListView;
    private  ThreeViewAdapter adapter;
    private  SuperTreeViewAdapter superAdapter;
    public String[] parent = { "山西", "四川","陕西","云南","上海","苏州","北京","河南","河北","浙江"};
    public String[][][]  child_grandson= {
            {{"运城"},
                    {"AA","AAA"}},
            {{"成都"},
                    {"BBB","BBBB","BBBBB"}},
            {{"C君"},
                    {"CCC","CCCC"}},
            {{"D君"},
                    {"DDD","DDDD","DDDDD"}},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_citylist);
        initView();
    }

    private void initView() {
        expandableListView= (ExpandableListView) findViewById(R.id.expandableListView);
        adapter=new ThreeViewAdapter(this,ThreeViewAdapter.PaddingLeft>>1);
        superAdapter=new SuperTreeViewAdapter(this, stvClickEvent);
        List<SuperTreeViewAdapter.SuperTreeNode> superTreeNode = superAdapter.GetTreeNode();
        for(int i=0;i<parent.length;i++)//第一层
        {
            SuperTreeViewAdapter.SuperTreeNode superNode=new SuperTreeViewAdapter.SuperTreeNode();
            superNode.parent=parent[i];

            //第二层
            for(int ii=0;ii<child_grandson.length;ii++)
            {
                ThreeViewAdapter.TreeNode node=new ThreeViewAdapter.TreeNode();
                node.parent=child_grandson[ii][0][0];//第二级菜单的标题

                for(int iii=0;iii<child_grandson[ii][1].length;iii++)//第三级菜单
                {
                    node.childs.add(child_grandson[ii][1][iii]);
                }
                superNode.childs.add(node);
            }
            superTreeNode.add(superNode);

        }
        superAdapter.UpdateTreeNode(superTreeNode);
        expandableListView.setAdapter(superAdapter);


    }
    /**
     * 三级树形菜单的事件不再可用，本函数由三级树形菜单的子项（二级菜单）进行回调
     */
    ExpandableListView.OnChildClickListener stvClickEvent=new ExpandableListView.OnChildClickListener(){

        @Override
        public boolean onChildClick(ExpandableListView parent,
                                    View v, int groupPosition, int childPosition,
                                    long id) {
            String str="parent id:"+String.valueOf(groupPosition)+",children id:"+String.valueOf(childPosition);
            Toast.makeText(CityListActivity.this, str, Toast.LENGTH_LONG).show();
            return false;
        }

    };
}
