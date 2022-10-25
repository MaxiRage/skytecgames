package Actions;

import Entity.Details;
import Entity.Treasury;
import Entity.Users;
import lombok.SneakyThrows;

/***
 * ��� ���������� � ���� ������������ ���������� ����� � ���� ������������ ������ �� ���������� 2
 */
public class JoiningClan {

    int rateFee = 2;

    @SneakyThrows
    public void feeTreasury(String nameUser) {

        int balanceUser = new Users().getUserBalance(nameUser);
        int amountForJoining = new Users().getSkillArena(nameUser) * rateFee;

        if (balanceUser > amountForJoining) {
            new Users().updateBalanceUsers(amountForJoining, nameUser);

            new Treasury().updateBalanceTreasury(amountForJoining);

            new Details().insertTo(nameUser, String.valueOf(EnamActions.JOININGCLAN), amountForJoining);

        } else System.out.println("�� ������� ������� �� ��������");

        System.out.printf("����� " + Thread.currentThread() + " � ������: " +
                        "������������ %s ������� � ���� \"PowerRangers\" � �������� %d � ����� \n"
                , nameUser, amountForJoining);
    }
}