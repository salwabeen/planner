(define (problem sokoban)
(:domain sokoban)
(:objects C1 C2 C3 C4 C5 C6 C7 C8 C9 C10 C11 C12 C13 C14 C15 C16 C17 C18 C19 C20 C21 C22 C23 C24 C25 C26 C27 C28 C29 C30 C31 C32 C33 C34 C35 C36 C37 C38 C39 C40 C41 C42 C43 C44 C45 C46 C47 C48 C49 - case)
(:INIT (not-hasguard C9) (not-hasblock C9) (not-hasguard C10) (not-hasblock C10) (not-hasguard C11) (not-hasblock C11) (not-hasguard C12) (not-hasblock C12) (not-hasguard C16) (not-hasblock C16) (not-hasguard C18) (not-hasblock C18) (not-hasguard C19) (not-hasblock C19) (not-hasguard C23) (not-hasblock C23) (not-hasguard C24) (not-hasblock C24) (hasblock C25)(not-hasguard C25) (hasblock C26)(not-hasguard C26) (not-hasguard C27) (not-hasblock C27) (hasblock C32) (not-hasguard C32) (hasguard C33) (not-hasblock C33) (not-hasguard C34) (not-hasblock C34) (not-hasguard C36) (not-hasblock C36) (not-hasguard C37) (not-hasblock C37) (not-hasguard C39) (not-hasblock C39) (not-hasguard C40) (not-hasblock C40) (not-hasguard C41) (not-hasblock C41) (not-hasguard C43) (not-hasblock C43) (not-hasguard C44) (not-hasblock C44) (isright C2 C1) (isright C3 C2) (isright C4 C3) (isright C5 C4) (isright C6 C5) (isright C7 C6) (isright C8 C7) (isright C9 C8) (isright C10 C9) (isright C11 C10) (isright C12 C11) (isright C13 C12) (isright C14 C13) (isright C15 C14) (isright C16 C15) (isright C17 C16) (isright C18 C17) (isright C19 C18) (isright C20 C19) (isright C21 C20) (isright C22 C21) (isright C23 C22) (isright C24 C23) (isright C25 C24) (isright C26 C25) (isright C27 C26) (isright C28 C27) (isright C29 C28) (isright C30 C29) (isright C31 C30) (isright C32 C31) (isright C33 C32) (isright C34 C33) (isright C35 C34) (isright C36 C35) (isright C37 C36) (isright C38 C37) (isright C39 C38) (isright C40 C39) (isright C41 C40) (isright C42 C41) (isright C43 C42) (isright C44 C43) (isright C45 C44) (isright C46 C45) (isright C47 C46) (isright C48 C47) (isright C49 C48) (isup C1 C8) (isup C2 C9) (isup C3 C10) (isup C4 C11) (isup C5 C12) (isup C6 C13) (isup C7 C14) (isup C8 C15) (isup C9 C16) (isup C10 C17) (isup C11 C18) (isup C12 C19) (isup C13 C20) (isup C14 C21) (isup C15 C22) (isup C16 C23) (isup C17 C24) (isup C18 C25) (isup C19 C26) (isup C20 C27) (isup C21 C28) (isup C22 C29) (isup C23 C30) (isup C24 C31) (isup C25 C32) (isup C26 C33) (isup C27 C34) (isup C28 C35) (isup C29 C36) (isup C30 C37) (isup C31 C38) (isup C32 C39) (isup C33 C40) (isup C34 C41) (isup C35 C42) (isup C36 C43) (isup C37 C44) (isup C38 C45) (isup C39 C46) (isup C40 C47) (isup C41 C48) (isup C42 C49) )
(:goal (and (hasblock C18) (hasblock C32) (hasblock C33) )))