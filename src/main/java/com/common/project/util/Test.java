package com.common.project.util;

import com.common.project.entity.TestEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * 关于无限分类问题
 */
public class Test {
        // 查询数据库菜单表中所有数据(这里模拟一个菜单表的所有数据)
        static int times=0;
        static List<TestEntity> data = new ArrayList<TestEntity>();
        static {
            TestEntity l = new TestEntity(1, "北京市", 0,1);

            TestEntity l2 = new TestEntity(3, "朝阳区", 1,2);
            TestEntity l4 = new TestEntity(5, "大郊亭中街", 3,3);
            TestEntity l11 = new TestEntity(12, "二号院", 5,4);
            TestEntity l12 = new TestEntity(13, "三号楼", 12,5);
            TestEntity l13 = new TestEntity(14, "四号楼", 12,5);
            TestEntity l5 = new TestEntity(6, "大望路", 3,3);
            TestEntity l6 = new TestEntity(7, "南磨房路", 3,3);
            TestEntity l3 = new TestEntity(4, "海淀区", 1,2);
            TestEntity l7 = new TestEntity(8, "唐家岭路", 4,3);
            TestEntity l8 = new TestEntity(9, "上地路", 4,3);

            TestEntity l1 = new TestEntity(2, "天津市", 0,1);
            TestEntity l9 = new TestEntity(10, "红桥区", 2,2);
            TestEntity l10 = new TestEntity(11, "北辰区", 2,2);

            data.add(l2);
            data.add(l);
            data.add(l1);
            data.add(l3);
            data.add(l10);
            data.add(l9);
            data.add(l5);
            data.add(l4);
            data.add(l7);
            data.add(l6);
            data.add(l8);
            data.add(l12);
            data.add(l11);
            data.add(l13);
        }

        public static void main(String[] args) {
            List<TestEntity> resultList = new ArrayList<>();
            System.out.println("查询前集合大小:"+data.size());
            long start = System.currentTimeMillis();
            for (TestEntity testEntity : data) {
                times++;
                if (testEntity.getParentId()==0) {//父级菜单开始添加
                    resultList.add(testEntity);
                    if (ifChilds(data, testEntity.getId())) {//存在子集
                        List<TestEntity> childs = new ArrayList<>();
                        childs = getChildList(data,testEntity.getId(),childs);
                        resultList.addAll(childs);
                    }
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("查询后集合大小:"+resultList.size());
            System.out.println("查询次数:"+times+"次");
            System.out.println("查询耗时:"+(end-start)+"毫秒");

            for (TestEntity testEntity : resultList) {
                String s = "";
                for (int i = 1; i < testEntity.getLevel(); i++) {
                    s = s + "\t";
                }
                System.out.println(s+"I____"+testEntity.getName());
            }

        }

        //获取父id下的子集合
        private static List<TestEntity> getChildList(List<TestEntity> list, int pId, List<TestEntity> reList) {
            for (TestEntity testEntity : list) {
                times++;
                if (testEntity.getParentId()==pId) {//查询下级菜单
                    reList.add(testEntity);
                    if (ifChilds(list, testEntity.getId())) {
                        getChildList(list, testEntity.getId(), reList);
                    }
                }
            }
            return reList;
        }

        //判断是否存在子集
        private static boolean ifChilds(List<TestEntity> list,int pId) {
            boolean flag = false;
            for (TestEntity testEntity : list) {
                times++;
                if (testEntity.getParentId()==pId) {
                    flag=true;
                    break;
                }
            }
            return flag;
        }
    }
