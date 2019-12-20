package projet_Raises;

import java.util.List;
import java.util.Scanner;

public class Create_Remove_Users {
    public Create_Remove_Users() {
    }
    private static int age;

    /**
     * create and add user,
     * ask user to step all information necessary
     *A user must be considered as already existing if the name AND the first name correspond perfectly to an former user .
     *In this case an error message will be displayed
     *
     *- La commande sera suivie de tous les paramètres définis plus haut pour un utilisateur
     * - La commande doit créer en mémoire un nouvel utilisateur.
     * - Un utilisateur devra être considéré comme déjà existant si le nom ET le prénom correspondent parfaitement à une entrée
     * déjà enregistrée. Dans ce cas un message d'erreur s'affichera
     * - La commande doit vérifier que les paramètres de types entiers sont bornés
     * - La commande ajoutera l'utilisateur dans une liste si toutes les conditions sont correctes
     * @param listUsers
     */
    public static void createAddUser(List<User> listUsers) {

        try {
            System.out.println("Enter your first name: ");
            Scanner sc = new Scanner(System.in);
            String firstName = sc.next();
            System.out.println("Enter your last name: ");
            Scanner scan = new Scanner(System.in);
            String name = scan.next();
            System.out.println("Enter your country: ");
            Scanner ct = new Scanner(System.in);
            String country = ct.next();
            do {
                try {
                    System.out.println("Enter your age (between 10 and 99): ");
                    Scanner a = new Scanner(System.in);
                    String ag = a.next();
                    age = Integer.parseInt(ag);
                }catch (NumberFormatException e){
                    System.out.println("Error, please enter a number for your age. ");
                    e.getMessage();
                }
            } while (age < 10 || age > 99);
            System.out.println("Enter your specialty: ");
            Scanner s = new Scanner(System.in);
            String specialty = s.next();
            User createAddUser = new User(firstName, name, country, age, specialty);

            if(!Finder.isUserExist(firstName, name, listUsers)){
                listUsers.add(createAddUser);
                System.out.println("The user has been created with success!");
            }
            else{
                System.out.println("Error: the user exist already, please try again.");
            }

        } catch (Exception e) {
            System.out.println("Error.");
            e.getMessage();
        }

    }

    /**
     * - this command searches for a user with the same name AND first name entered and will delete it from the list.
     * - if the user sought does not exist, then an error message is displayed stating that it cannot be found.
     * - if the user exists, it is deleted from the list in memory, and a message of successful deletion is displayed.
     * - if the user is used in the third table, then this user cannot be deleted and an error message will be displayed
     *
     * - cette commande recherche un utilisateur avec le meme nom ET prénom rentrés et va le supprimer de la liste.
     * - si l'utilisateur recherché n'existe pas, alors on affiche un message d'erreur comme quoi il est introuvable.
     * - si l'utilisateur existe, il est supprimé de la liste en mémoire, et on affiche un message de réussite de l'effacement.
     * - si l'utilisateur est utilisé dans la troisième table (emprunts, locations, enchères, livraisons, actions, transactions),
     * alors l'effacement de cet utilisateur ne pourra pas se faire et un message d'erreur sera affiché
     * @param listUsers
     * @param listRaise
     */

    public static void removeUser(List<User> listUsers,List<Raises> listRaise){
        System.out.println("First name of the user who you wants to delete.");
        Scanner f = new Scanner(System.in);
        String firstName = f.next();
        System.out.println("Last name of the user who you wants to delete.");
        Scanner l = new Scanner(System.in);
        String lastName = l.next();

        if (Finder.isUserExist(firstName, lastName, listUsers)) {
                /**
                 *if the user is used in the third raises table,
                 * then the deletion of this user cannot be done and an error message will be displayed
                 *
                * si l'utilisateur est utilisé dans la troisième table enchères,,
                *  alors l'effacement de cet utilisateur ne pourra pas se faire et un message d'erreur sera affiché
                 *  */
                if(Finder.isUserExistInRaisesList(firstName,lastName,listRaise)){
                    System.out.println("The user have a raise, you can't delete the user. ");

                }else {
                    listUsers.remove(Finder.findTheUserExistInList(firstName, lastName, listUsers));
                    System.out.println("The user has been deleted. ");
                }

            } else {
            System.out.println("Error: the user is not found in our list users, please try again.");
        }
    }
}
