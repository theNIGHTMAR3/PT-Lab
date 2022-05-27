using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Lab2_CS
{
	/// <summary>
	/// Logika interakcji dla klasy CreationDialog.xaml
	/// </summary>
	public partial class CreationDialog : Window
	{
		public string path { get; set; }
		public bool finished { get; set; }
		public CreationDialog(string path)
		{
			this.path = path;
			InitializeComponent();
		}

        //executes when clicked on buttom
        private void OnClick(Object sender, RoutedEventArgs e)
        {
            bool correctName = true;

            // regex, checking if filename is correct
            if ((bool)isFile.IsChecked && !Regex.IsMatch(fileName.Text, "^[a-zA-Z0-9_~-]{1,8}\\.(txt|java|temp|asm|py|php|html)$"))
            {
                string temp = "Nie poprawne znaki w nazwie";
                System.Windows.MessageBox.Show(temp, "Alert", MessageBoxButton.OK, MessageBoxImage.Information);
                correctName = false;
                Close();
            }

            //handle file operation if filename is good
            if(correctName)
			{
                //adding \ to path
                this.path = this.path + "\\" + fileName.Text;

                //setting RAHS attributes to new file
                FileAttributes attributes = FileAttributes.Normal;
                if ((bool)isHidden.IsChecked)
                {
                    attributes |= FileAttributes.Hidden;
                }
                if ((bool)isSystem.IsChecked)
                {
                    attributes |= FileAttributes.System;
                }
                if ((bool)isReadOnly.IsChecked)
                {
                    attributes |= FileAttributes.ReadOnly;
                }
                if ((bool)isArchive.IsChecked)
                {
                    attributes |= FileAttributes.Archive;
                }
                //if file, create a file
                if ((bool)isFile.IsChecked)
                {
                    File.Create(path);
                }
                //else create dir
                else
                {
                    Directory.CreateDirectory(path);
                }
                //set attributes to file
                File.SetAttributes(path, attributes);

                //set dialog to finish
                this.finished = true;
                Close();
            }
            Close();
		}
            

        //closing window
        private void CancelOnClick(Object sender, RoutedEventArgs e)
		{
			Close();
		}


		private void isSystem_Checked(object sender, RoutedEventArgs e)
		{

		}

		private void RadioButton_Checked(object sender, RoutedEventArgs e)
		{

		}

		private void Button_Click(object sender, RoutedEventArgs e)
		{

		}
	}
}
