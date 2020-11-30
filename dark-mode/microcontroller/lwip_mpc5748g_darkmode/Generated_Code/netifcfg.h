
/* ###################################################################
**     This component module is generated by Processor Expert. Do not modify it.
**     Filename    : tcpip_middleware.h
**     Project     : lwip_mpc5748g_darkmode
**     Processor   : MPC5748G_176
**     Component   : tcpip
**     Version     : Component SDK_S32_PA_15, Driver 01.00, CPU db: 3.00.000
**     Repository  : SDK_S32_PA_15
**     Compiler    : GNU C Compiler
**     Date/Time   : 2020-11-20, 16:58, # CodeGen: 0
**     Contents    :
**         No public methods
**
**     Copyright 1997 - 2015 Freescale Semiconductor, Inc. 
**     Copyright 2016-2017 NXP 
**     All Rights Reserved.
**     
**     THIS SOFTWARE IS PROVIDED BY NXP "AS IS" AND ANY EXPRESSED OR
**     IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
**     OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
**     IN NO EVENT SHALL NXP OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
**     INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
**     (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
**     SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
**     HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
**     STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
**     IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
**     THE POSSIBILITY OF SUCH DAMAGE.
** ###################################################################*/
/*!
** @file tcpip_middleware.h
** @version 01.00
*/         
/*!
**  @addtogroup tcpip_middleware_module tcpip_middleware module documentation
**  @{
*/         

#ifndef LWIP_NETIFCFG_H
#define LWIP_NETIFCFG_H

#include "lwip/netif.h"
#include "enetif.h"

/*
  Structure containing enet interfaces configuration.
 */
typedef struct  {
    u8_t num;                               /* Interface number */
    uint8_t hwaddr[NETIF_MAX_HWADDR_LEN];   /* MAC address */
    bool has_dhcp;                          /* Variable containing information whether dhcp is enabled or not */
    bool has_auto_ip;                       /* Variable containing information whether auto ip is enabled or not */
    bool has_IPv6;                          /* Variable containing information whether ipv6 is enabled or not */
    u8_t ip_addr[4];                        /* Board's ip address. Needs to be set if dhcp is turned off. */
    u8_t netmask[4];                        /* Board's netmask. Needs to be set if dhcp is turned off. */
    u8_t gw[4];                             /* Board's network gateway. Needs to be set if dhcp is turned off. */
    const char *hostname;                   /* Board's hostname used for dns. */
    char name[2];                           /* Interface name */
} netif_custom_t ;


/* Number of active network interfaces */
#define ENETIF_NUMBER 1

/* Number of Ethernet Interfaces for the stack */
#define ETHIF_NUMBER ENETIF_NUMBER

/* Network interfaces configuration */
extern netif_custom_t *netif_cfg[ENETIF_NUMBER];

#endif /* LWIP_NETIFCFG_H */

/*!
** @}
*/
/*
** ###################################################################
**
**     This file was created by Processor Expert 10.1 [05.21]
**     for the NXP C55 series of microcontrollers.
**
** ###################################################################
*/

