package utils;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.List;

import domain.entity.Account;
import handle.HandleAccount;
import setupFile.AllFile;
import utils.constant.RoleEnum;

// class nay mục đích là để tạo ra super admin, vì không thể tạo người dùng với vai trò admin được
public class Initialize {
    public void init() {
        Long code = 0L;
        List<Account> accList = new HandleAccount().read(AllFile.fileAccountTxt);
        boolean existsByNameAndEmail = accList.stream()
                .anyMatch(x -> x.getEmail().equals("admin@gmail.com"));
        if (!existsByNameAndEmail) {

            if (accList.isEmpty()) {
                code = 0L;
            } else {
                for (Account x : accList) {
                    if (x.getId() >= code) {
                        code = x.getId();
                    }
                }
            }
            new HandleAccount().addNew(AllFile.fileAccountTxt,
                    new Account(code + 1, "admin", "123456", "admin@gmail.com", RoleEnum.ADMIN));
        }
    }
}
