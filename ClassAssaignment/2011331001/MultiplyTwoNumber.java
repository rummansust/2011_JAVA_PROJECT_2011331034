import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class MultiplyTwoNumber
{

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        int n,ans,a,b;
        try
        {
			n=in.nextInt();
            for(int it=0;it<n;it++)
            {
                a=in.nextInt();
                b=in.nextInt();
                ans=a*b;
                System.out.println(ans);
				it=0;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception here");
        }
    }
}

