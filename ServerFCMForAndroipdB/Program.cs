using FirebaseAdmin;
using Google.Apis.Auth.OAuth2;

namespace ServerFCMForAndroipdB
{
    internal static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // To customize application configuration such as set high DPI settings or default font,
            // see https://aka.ms/applicationconfiguration.
            ApplicationConfiguration.Initialize();
            FirebaseApp.Create(new AppOptions()
            {
                Credential = GoogleCredential.FromFile(@"C:\Users\gusbe\Downloads\clase2023a-firebase-adminsdk-2pbsh-24b2c7f6e5.json"),
            });

            Application.Run(new Form1());
        }
    }
}