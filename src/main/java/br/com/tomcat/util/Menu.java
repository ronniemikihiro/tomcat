package br.com.tomcat.util;

import br.com.tomcat.entity.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

public class Menu {

    private DefaultMenuModel menuModel;

    public Menu(final Usuario usuario) {
        menuModel = new DefaultMenuModel();

        final DefaultSubMenu subMenuCadastro = new DefaultSubMenu("Cadastro", "fa fa-edit");
        subMenuCadastro.addElement(createMenuItem("Usuário", "fa fa-user", "usuario"));
        subMenuCadastro.addElement(createMenuItem("Cliente", "fa fa-users", "cliente"));
        subMenuCadastro.addElement(createMenuItem("Item Pedido", "fa fa-align-justify", "itemPedido"));
        subMenuCadastro.addElement(createMenuItem("Pedido", "fa fa-edit", "pedido"));

        final DefaultSubMenu subMenuUserLogin = new DefaultSubMenu(null, "fa fa-user");

        final DefaultMenuItem menuItemUserLogin = new DefaultMenuItem();
        menuItemUserLogin.setGlobal(false);
        menuItemUserLogin.setValue(usuario.getNome());
        menuItemUserLogin.setStyle("cursor:default; background:none;");
        subMenuUserLogin.addElement(menuItemUserLogin);

        final DefaultMenuItem menuItemLogout = createMenuItem("Sair", "fa fa-power-off", null);
        menuItemLogout.setCommand("#{loginMB.logout}");
        subMenuUserLogin.addElement(menuItemLogout);

        menuModel.addElement(subMenuCadastro);
        menuModel.addElement(subMenuUserLogin);
    }

    private DefaultMenuItem createMenuItem(final String nomeItem, final String icon, final String page) {
        final DefaultMenuItem menuItem = new DefaultMenuItem(nomeItem, icon, StringUtil.isNullEmpty(page) ? null : "/pages/" + page +".xhtml");
        menuItem.setOnclick("PF('statusDialog').show()");
        menuItem.setOncomplete("PF('statusDialog').hide()");
        return menuItem;
    }


    public DefaultMenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(DefaultMenuModel menuModel) {
        this.menuModel = menuModel;
    }
}
