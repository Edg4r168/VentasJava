
document.addEventListener("DOMContentLoaded", function () {

    const $$toggleCheckbox = document.querySelectorAll(".toggleSubmenu");
    let timeoutId;

    $$toggleCheckbox.forEach((checkbox) => {
      checkbox.addEventListener("change", function () {
        const parent = checkbox.parentElement;
        const submenu = parent.nextElementSibling;

        if (checkbox.checked) {
          submenu.style.height = `${submenu.scrollHeight}px`;

          timeoutId && clearTimeout(timeoutId);

          timeoutId = setTimeout(() => {
            submenu.style.height = "auto";
          }, 300);
        } else {
          submenu.style.height = `${submenu.scrollHeight}px`;

          timeoutId && clearTimeout(timeoutId);

          timeoutId = setTimeout(() => {
            submenu.style.height = 0;
          }, 10);
        }
      });
    });

<!-- Resaltar links -->
    const navLinks = document.querySelectorAll("aside .nav-link") ?? [];
    const navLinksSub =
      document.querySelectorAll("aside .nav-link-sub") ?? [];

    const navs = [...navLinks, ...navLinksSub];

    let shouldUpdateNavState = true;

    navs.forEach((navLink) => {
      navLink.addEventListener("click", function () {
        shouldUpdateNavState = false;

        navs.forEach((link) => {
          link.classList.remove("active");
        });

        this.classList.add("active");
      });
    });

    const markSubmenuCheckboxes = (el) => {
      const checkbox = el
        .closest(".nav-item-expand")
        ?.querySelector(".toggleSubmenu");

      if (checkbox) {
        console.log(checkbox);
        checkbox.checked = true;
      }
    };

    const updateNavLinkState = () => {
      if (shouldUpdateNavState) {
        navs.forEach((navLink) => {
          navLink.classList.remove("active");
        });

        const nav = navs.find((el) => el.href === window.location.href);

        if (nav) {
          nav.classList.add("active");

          nav.scrollIntoView({
            behavior: "smooth",
            block: "center",
            // inline: "nearest",
          });

          // Marcar el checkbox del enlace activo
          // markSubmenuCheckboxes(nav);

          // Marcar todos los ancestros del enlace activo
          let parent = nav.closest(".nav-item-expand");
          while (parent) {
            markSubmenuCheckboxes(parent);
            parent =
              parent.previousElementSibling.closest(".nav-item-expand");
          }
        }
      }

      shouldUpdateNavState = true;
    };

    updateNavLinkState();

    window.addEventListener("popstate", updateNavLinkState);

<!-- Mostrar menu usuario -->
    const toggleInput = document.getElementById("input-toggleMenu");
    const menuContainer = document.querySelector(".menu-container") ?? [];
    const containerChildren = [...menuContainer.children];

    const handleClickEvent = (e) => {
      const clickedElement = e.target;

      const isClickedElementChildOfMenu = containerChildren.some((el) =>
        el.contains(clickedElement)
      );

      if (!isClickedElementChildOfMenu) {
        toggleInput.checked = false;
        document.removeEventListener("click", handleClickEvent);
      }
    };

    const deployMenuButton = containerChildren.find(
      (el) => el.className === "deployMenu"
    );

    if (deployMenuButton) {
      deployMenuButton.addEventListener("click", () => {
        document.addEventListener("click", handleClickEvent);
      });
    }

    const btnsideBar = document.getElementById("btn-deploySidebar");
    const sideBar = document.querySelector(".sideBar");
    const gridContainer = document.querySelector(".wrapper");
    const bgCloseSidebar = document.querySelector(".bg-close-sidebar");

    btnsideBar.addEventListener("click", function () {
      if (window.innerWidth < 900) {
        bgCloseSidebar.classList.toggle("close");
        sideBar.classList.toggle("close");
      } else {
        sideBar.classList.toggle("aside-small");
        gridContainer.classList.toggle("aside-small");
      }
    });

    bgCloseSidebar.addEventListener("click", function () {
      bgCloseSidebar.classList.toggle("close");
      sideBar.classList.toggle("close");
    });

<!-- Scroll -->
  const supportsWebkitScrollbar =
    "WebkitAppearance" in document.documentElement.style;
  const containers = document.querySelectorAll(".scroll") ?? [];

  containers.forEach((container) => {
    if (supportsWebkitScrollbar) {
      container.classList.add("custom-scroll");
    } else {
      container.classList.add("native-scroll");
    }
  });
});
