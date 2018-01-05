package cn.RailgunHamster.FinalHuluwaProject.gui;

import cn.RailgunHamster.FinalHuluwaProject.module.Unit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MapControllerProxy {
    private MapControllerInvocationHandler mapControllerInvocationHandler;

    MapControllerProxy(MapControllerProtocol mapControllerProtocol) {
        mapControllerInvocationHandler = new MapControllerInvocationHandler(mapControllerProtocol);
    }

    /**
     * @return 获取Map的代理
     */
    MapControllerProtocol create() {
        return (MapControllerProtocol) Proxy.newProxyInstance(
                MapControllerProtocol.class.getClassLoader(),
                new Class[]{MapControllerProtocol.class},
                mapControllerInvocationHandler);
    }

    /**
     * 将战斗过程序列化并写入文件
     */
    @SuppressWarnings({"unchecked"})
    void record() {
        ObjectOutputStream save = new Materials().recordSave();
        List<Pair<Position, Position>> record = mapControllerInvocationHandler.record;
        if (record == null) {
            System.out.println("save file open error");
            return;
        }
        try {
            Pair<Position, Position>[] records = new Pair[record.size()];
            record.toArray(records);
            save.writeObject(records);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            record.clear();
        }
    }

    /**
     * 解析保存的战斗
     */
    @SuppressWarnings({"unchecked"})
    void replay() {
        ObjectInputStream replay = new Materials().recordRead();
        List<Pair<Position, Position>> records = new ArrayList<>();
        try {
            Pair<Position, Position>[] recordsArray = (Pair<Position, Position>[]) (replay.readObject());
            records.addAll(Arrays.asList(recordsArray));
        } catch (IOException ioe) {
            System.out.println("replay file error");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("replay read error");
        }

        if (records.size() == 0) {
            System.out.println("record not found");
            return;
        }

        try {
            MapControllerProtocol map = mapControllerInvocationHandler.mapControllerProtocol;

            for (Pair<Position, Position> pair : records) {
                map.move(map.observe(pair.getKey()), pair.getValue());
                Thread.sleep(100);
            }
        } catch (InterruptedException ie) {
            System.out.println("interrupted");
        }
    }

    /**
     * Invocation Handler
     */
    class MapControllerInvocationHandler implements InvocationHandler {
        /**
         * 记录所有对方法的调用
         */
        private List<Pair<Position, Position>> record = new ArrayList<>();
        private MapControllerProtocol mapControllerProtocol;

        MapControllerInvocationHandler(MapControllerProtocol mapControllerProtocol) {
            this.mapControllerProtocol = mapControllerProtocol;
        }

        @Override
        public synchronized Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("move"))
                record.add(new Pair<>(((Unit) args[0]).getPosition(), (Position) args[1]));
            return method.invoke(mapControllerProtocol, args);
        }
    }


}
