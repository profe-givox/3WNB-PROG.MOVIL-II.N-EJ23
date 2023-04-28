using FirebaseAdmin.Messaging;

namespace ServerFCMForAndroipdB
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private async void button1_ClickAsync(object sender, EventArgs e)
        {
            // This registration token comes from the client FCM SDKs.
            var registrationToken = textBox1.Text;

            // See documentation on defining a message payload.
            var message = new FirebaseAdmin.Messaging.Message()
            {
                Notification = new Notification()
                {
                    Title = textBox2.Text,
                    Body = textBox3.Text,
                },

                Data = new Dictionary<string, string>()
                        {
                            { "score", "850" },
                            { "time", "2:45" },
                        },
                Token = registrationToken,
            };

            // Send a message to the device corresponding to the provided
            // registration token.
            string response = await FirebaseMessaging.DefaultInstance.SendAsync(message);
            // Response is a message ID string.
            Console.WriteLine("Successfully sent message: " + response);

        }
    }
}