package com.lf.thread;

import android.os.AsyncTask;

/*


  * 步骤2：创建AsyncTask子类的实例对象（即 任务实例）
  * 注：AsyncTask子类的实例必须在UI线程中创建

  MyTask mTask = new MyTask();


 * 步骤3：手动调用execute(Params... params) 从而执行异步线程任务
 * 注：
 *    a. 必须在UI线程中调用
 *    b. 同一个AsyncTask实例对象只能执行1次，若执行第2次将会抛出异常
 *    c. 执行任务中，系统会自动调用AsyncTask的一系列方法：onPreExecute() 、doInBackground()、onProgressUpdate() 、onPostExecute()
 *    d. 不能手动调用上述方法
 *
    mTask.execute()；


    4. 关于 内存泄漏
         结论
               若AsyncTask被声明为Activity的非静态内部类，当Activity需销毁时，
               会因AsyncTask保留对Activity的引用 而导致Activity无法被回收，最终引起内存泄露
         使用建议
               AsyncTask应被声明为Activity的静态内部类


   5. 线程任务执行结果 丢失

        结论
                当Activity重新创建时（屏幕旋转 / Activity被意外销毁时后恢复），
                之前运行的AsyncTask（非静态的内部类）持有的之前Activity引用已无效，
                故复写的onPostExecute()将不生效，即无法更新UI操作
        使用建议
                在Activity恢复时的对应方法 重启 任务线程

 */

public class MyTask extends AsyncTask {


    /**
     *  作用：执行 线程任务前的操作
     *       根据需求复写
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     *  作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
     *       必须复写，从而自定义线程任务
     * @param objects
     * @return
     */
    @Override
    protected Object doInBackground(Object[] objects) {

        int count = 0;

        // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
        publishProgress(count);

        return null;
    }


    /**
     * 作用：在主线程 显示线程任务执行的进度
     *      根据需求复写
     * @param values
     */
    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }


    /**
     * 作用：接收线程任务执行结果、将执行结果显示到UI组件
     *      必须复写，从而自定义UI操作
     * @param o
     */
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    /**
     * 作用：将异步任务设置为：取消状态
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
