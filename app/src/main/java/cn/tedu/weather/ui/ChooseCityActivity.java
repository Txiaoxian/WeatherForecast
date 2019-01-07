package cn.tedu.weather.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.weather.R;
import cn.tedu.weather.entity.City;
import cn.tedu.weather.entity.Province;
import cn.tedu.weather.util.ToastUtil;


public class ChooseCityActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    List<Province>provinces;//省份数据集合
    List<City>cities;//城市数据集合
    List<City>usingCities;//要显示的城市的集合
    ListView lvProvinces;
    ListView lvCities;
    private ArrayAdapter<City> usingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        lvProvinces= (ListView) findViewById(R.id.lv_provinces);
        lvCities= (ListView) findViewById(R.id.lv_cities);
        //为2个ListView添加监听器
        lvCities.setOnItemClickListener(this);
        lvProvinces.setOnItemClickListener(this);
        provinces=new ArrayList<>();
        cities=new ArrayList<>();
        usingCities=new ArrayList<>();
        //解析省份和城市的xml文件
        try {
            parseProvinceXml();
            parseCityXml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //解析省份
    private void parseProvinceXml() throws Exception {
//        使用Dom4j解析的步骤
        SAXReader reader=new SAXReader();
        Document document = reader.read(getAssets().open("Provinces.xml"));
        Element rootElement = document.getRootElement();//获取根元素
//        getName()  返回标签名
//        getText()  返回标签文本
//        elements() 返回所有的子标签
//        attributeValue("a") 获取a属性的属性值
        List <Element> es = rootElement.elements();//获取所有子标签
//        for(int i=0;i<es.size();i++){
//            Element e=es.get(i);
//            String id = e.attributeValue("ID");
//            String provinceName=e.attributeValue("ProvinceName");
//        }
        for(Element e:es){//增强for循环
            String id = e.attributeValue("ID");
            String provinceName=e.attributeValue("ProvinceName");
            Province p=new Province();
            p.setId(id);
            p.setProvinceName(provinceName);
            provinces.add(p);//将省份对象添加到省份集合
        }
        ArrayAdapter<Province>provinceAdapter
     =new ArrayAdapter<>
     (this,R.layout.list_item_city_province_name,provinces);
    lvProvinces.setAdapter(provinceAdapter);
    }
    //解析城市
    private void parseCityXml() throws Exception{
//        使用Dom4j解析的步骤
        SAXReader reader=new SAXReader();
        Document document = reader.read(getAssets().open("Cities.xml"));
        Element rootElement = document.getRootElement();//获取根元素
//        getName()  返回标签名
//        getText()  返回标签文本
//        elements() 返回所有的子标签
//        attributeValue("a") 获取a属性的属性值
        List <Element> es = rootElement.elements();//获取所有子标签
//        for(int i=0;i<es.size();i++){
//            Element e=es.get(i);
//            String id = e.attributeValue("ID");
//            String provinceName=e.attributeValue("ProvinceName");
//        }
        for(Element e:es){//增强for循环
            String id = e.attributeValue("ID");
            String cityName=e.attributeValue("CityName");
            String pid=e.attributeValue("PID");
            City c=new City();
            c.setId(id);
            c.setCityName(cityName);
            c.setPid(pid);
            cities.add(c);//将城市对象添加到城市集合
        }
    }

    /**
     *
     * @param adapterView 表示点击哪个ListView导致该方法被执行
     * @param view         点击的哪个列表项
     * @param position     点击的列表项所在的位置
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            switch (adapterView.getId()){
                case R.id.lv_provinces:
                    Province province = provinces.get(position);//获取点击对应列表项的城市
                    //找到对应省份的城市
                    //清空usingCities
                    usingCities.clear();//清空集合
                    for(City c:cities){//遍历所有城市
                        if(province.getId().equals(c.getPid())){
                            //将省份对应的城市放到集合中
                            usingCities.add(c);
                        }
                    }
                    if(usingAdapter==null) {
                        usingAdapter =
                                new ArrayAdapter<>(this, R.layout.list_item_city_province_name, usingCities);
                        lvCities.setAdapter(usingAdapter);
                    }else{
                        usingAdapter.notifyDataSetChanged();//通知adapter数据源发生了改变
                    }
                    break;
                case R.id.lv_cities:
                    City city = usingCities.get(position);
                    ToastUtil.showToast(this,city.getCityName());
                    break;
            }
    }
}
