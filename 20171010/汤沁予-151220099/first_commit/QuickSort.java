public class QuickSort implements Sorter
{
    Comparable[] objs;
    public void Sort(Comparable[] objs)
    {
        this.objs = objs;
        QuickSort(0,objs.length-1);
    }
    void QuickSort(int low, int high)
    {
        int PivotPost;
        if (low<high)
        {
            PivotPost = Part(low, high);
            QuickSort(low, PivotPost-1);
            QuickSort(PivotPost + 1, high);
        }
    }

    int Part(int low, int high)
    {
        int vacant, unknown;
        Comparable pivot = objs[low];
        vacant = low;
        for (unknown = low + 1; unknown <= high; unknown++)
        {
            //if (array[unknown] < pivot)
            if(objs[unknown].biggerthan(pivot)==false)
            {
                objs[vacant] = objs[unknown];
                if(unknown != vacant + 1)
                {
                    objs[unknown] = objs[vacant + 1];
                }
                vacant++;
            }
        }
        objs[vacant] = pivot;
        return vacant;
    }

}
