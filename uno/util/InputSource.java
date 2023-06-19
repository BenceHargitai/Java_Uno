package uno.util;
import java.io.*;

public class InputSource
{
    public final boolean isInteractive;
    private BufferedReader br;
    private int[] inputs;
    private int inputIdx;
    public static final int DONE = -1;
    
    public InputSource(boolean isInteractive, int... inputs)
    {
        this.isInteractive = isInteractive;
        if (isInteractive)
        {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        else
        {
            br = null;
            this.inputs = inputs;
        }
        inputIdx = 0;
    }
    public int getNextInput()
    {
        if (isInteractive)
        {
            try
            {
                return Integer.parseInt(br.readLine());
            }
            catch (IOException e)
            {
                return DONE;
            }
        }
        else
        {
            if (inputIdx >= inputs.length)
            {
                return DONE;
            }
            return inputs[inputIdx++];
        }


    }
}